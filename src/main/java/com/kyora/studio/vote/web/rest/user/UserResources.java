package com.kyora.studio.vote.web.rest.user;

import com.kyora.studio.vote.config.ApiConstant;
import com.kyora.studio.vote.domain.user.User;
import com.kyora.studio.vote.exception.UsernameExistsException;
import com.kyora.studio.vote.security.AuthoritiesConstants;
import com.kyora.studio.vote.service.user.UserCriteria;
import com.kyora.studio.vote.service.user.UserQueryService;
import com.kyora.studio.vote.service.user.UserService;
import com.kyora.studio.vote.util.PaginationUtil;
import com.kyora.studio.vote.web.dto.user.UserCreateDTO;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

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


    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria writer the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUser(UserCriteria criteria, @ApiIgnore Pageable pageable) {
        final Page<User> page = userQueryService.findByCriteriaWithPage(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, ApiConstant.API_V1 + ApiConstant.USER.ROOT);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
