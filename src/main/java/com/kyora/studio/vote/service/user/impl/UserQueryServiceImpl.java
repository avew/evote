package com.kyora.studio.vote.service.user.impl;

import com.kyora.studio.vote.domain.User;
import com.kyora.studio.vote.exception.UserNotFoundException;
import com.kyora.studio.vote.repository.UserRepository;
import com.kyora.studio.vote.security.SecurityUtils;
import com.kyora.studio.vote.service.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User findById(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    @Override
    public Optional<User> getUserWithAuthoritiesByLogin() {
        return userRepository.findOneWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin());
    }

    @Override
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }
}
