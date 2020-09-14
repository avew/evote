package com.kyora.studio.vote.web.rest.user;

import com.kyora.studio.vote.config.ApiConstant;
import com.kyora.studio.vote.domain.User;
import com.kyora.studio.vote.exception.UsernameExistsException;
import com.kyora.studio.vote.security.AuthoritiesConstants;
import com.kyora.studio.vote.service.user.UserQueryService;
import com.kyora.studio.vote.service.user.UserService;
import com.kyora.studio.vote.web.dto.user.UserCreateDTO;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> creatUser(@Valid @RequestBody UserCreateDTO managedUserVM) throws UsernameExistsException {
        log.info("REST REQUEST CREATE USER: {}", managedUserVM);
        User newUser = userService.createUserWithAuthoritiesUser(managedUserVM);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping
    @Timed
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
        log.info("REST REQUEST TO UPDATE USER : {}", user);
        User saved = userService.updateUser(user);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

}
