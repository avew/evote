package com.kyora.studio.vote.service.user.impl;

import com.kyora.studio.vote.domain.User;
import com.kyora.studio.vote.repository.UserRepository;
import com.kyora.studio.vote.service.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findOneByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserWithAuthoritiesByLogin() {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Override
    public User getUserWithAuthorities(Long id) {
        return null;
    }

    @Override
    public User getUserWithAuthorities() {
        return null;
    }

    @Override
    public List<String> getAuthorities() {
        return null;
    }

    @Override
    public int countById(String userId) {
        return 0;
    }

    @Override
    public String findUsernameById(String userId) {
        return null;
    }
}
