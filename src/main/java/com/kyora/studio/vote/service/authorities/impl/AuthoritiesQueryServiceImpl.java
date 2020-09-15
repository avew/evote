package com.kyora.studio.vote.service.authorities.impl;

import com.kyora.studio.vote.domain.user.Authority;
import com.kyora.studio.vote.exception.AuthorityNotFoundException;
import com.kyora.studio.vote.repository.user.AuthorityRepository;
import com.kyora.studio.vote.service.authorities.AuthoritiesQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthoritiesQueryServiceImpl implements AuthoritiesQueryService {

    private final AuthorityRepository repository;

    @Override
    public List<String> getAuthoritiesName() {
        return repository.findAll().stream().map(Authority::getName).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Authority findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new AuthorityNotFoundException(String.format("authority %s not found", name)));
    }
}
