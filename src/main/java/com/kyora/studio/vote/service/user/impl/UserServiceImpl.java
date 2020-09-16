package com.kyora.studio.vote.service.user.impl;

import com.kyora.studio.vote.domain.user.Authority;
import com.kyora.studio.vote.domain.user.User;
import com.kyora.studio.vote.exception.UserNotFoundException;
import com.kyora.studio.vote.exception.UsernameExistsException;
import com.kyora.studio.vote.repository.user.UserRepository;
import com.kyora.studio.vote.security.AuthoritiesConstants;
import com.kyora.studio.vote.service.authorities.AuthoritiesQueryService;
import com.kyora.studio.vote.service.user.UserQueryService;
import com.kyora.studio.vote.service.user.UserService;
import com.kyora.studio.vote.util.RandomUtil;
import com.kyora.studio.vote.web.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserQueryService userQueryService;
    private final AuthoritiesQueryService authoritiesQueryService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User createUserWithAuthoritiesUser(UserCreateDTO dto) throws UsernameExistsException {

        Optional<User> findByLogin = userQueryService.findByLogin(dto.getLogin());
        if (findByLogin.isPresent()) {
            throw new UsernameExistsException(String.format("username %s is exists", dto.getLogin()));
        }

//        userQueryService.findByEmail(dto.get)

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authoritiesQueryService.findByName(AuthoritiesConstants.USER));

        User user = User.builder()
                .login(dto.getLogin())
                .firstName(StringUtils.capitalize(dto.getFirstName()))
                .lastName(StringUtils.capitalize(dto.getLastName()))
                .email(dto.getEmail().toLowerCase())
                .langKey("en")
                .authorities(authorities)
                .password(passwordEncoder.encode(dto.getLogin()))
                .resetKey(RandomUtil.generateResetKey())
                .resetDate(Instant.now())
                .activated(true)
                .build();
        User saved = userRepository.save(user);
        log.debug("CREATED INFORMATION USER: {}", saved);

        return saved;
    }

    @Override
    public User updateUser(User userDTO) {
        return userQueryService.findById(userDTO.getId())
                .map(user -> {
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setEmail(userDTO.getEmail());
                    user.setImageUrl(userDTO.getImageUrl());
                    user.setActivated(userDTO.isActivated());
                    user.setLangKey(userDTO.getLangKey());

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException("user not found"));

    }

    @Override
    public void deleteById(String id) {
        log.debug("REQ DELETE BY ID: {}", id);
        userRepository.deleteById(id);
    }
}
