package com.kyora.studio.vote.service.authorities;

import com.kyora.studio.vote.domain.Authority;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AuthoritiesQueryService {

    List<String> getAuthoritiesName();

    Authority findByName(String name);
}
