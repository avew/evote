package com.kyora.studio.vote.web.rest.user;

import com.kyora.studio.vote.config.ApiConstant;
import com.kyora.studio.vote.domain.User;
import com.kyora.studio.vote.security.AuthoritiesConstants;
import com.kyora.studio.vote.service.user.UserQueryService;
import com.kyora.studio.vote.service.user.UserService;
import com.kyora.studio.vote.web.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiConstant.API_V1 + ApiConstant.USER.ROOT)
@Slf4j
@RequiredArgsConstructor
public class UserResources {

    private final UserService userService;
    private final UserQueryService userQueryService;


    @Secured({AuthoritiesConstants.ADMIN})
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> postCreate(@Valid @RequestBody UserCreateDTO managedUserVM) {
        log.info("saving new user");
        log.info(managedUserVM.toString());

//        String login = managedUserVM.getLogin().toLowerCase();
//        String email = managedUserVM.getEmail().toLowerCase();

//        Optional<User> userByLogin = userQueryService.findOneByLogin(login);
//        Optional<User> userByEmail = userQueryService.findOneByEmail(email);
//
//        if (userByLogin.isPresent()) {
//            String message = String.format("Username '%s' sudah dipakai user '%s'", login, userByLogin.get().getLogin());
//
//            throw new ExistsException(message);
//        }
//
//        if (userByEmail.isPresent()) {
//            String message = String.format("Email '%s' sudah dipakai user '%s'", email, userByEmail.get().getLogin());
//
//            throw new ExistsException(message);
//        }

        User newUser = userService.createUserWithAuthoritiesUser(managedUserVM);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
