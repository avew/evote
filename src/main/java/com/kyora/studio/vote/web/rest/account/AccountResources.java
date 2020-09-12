package com.kyora.studio.vote.web.rest.account;

import com.kyora.studio.vote.config.ApiConstant;
import com.kyora.studio.vote.domain.User;
import com.kyora.studio.vote.service.user.UserQueryService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API_V1 + ApiConstant.ACCOUNT.ROOT)
@Slf4j
@RequiredArgsConstructor
public class AccountResources {

    private final UserQueryService userQueryService;

    @GetMapping(ApiConstant.ACCOUNT.ME)
    @Timed
    public User getAccount() {
        return userQueryService.getUserWithAuthoritiesByLogin().get();
    }

}
