package com.kyora.studio.vote.service.user;

import com.kyora.studio.vote.domain.user.User;
import com.kyora.studio.vote.util.BaseNonTransactional;

import java.util.Optional;

/**
 * Created by avew on 1/15/18.
 */

public interface UserQueryService extends BaseNonTransactional<User, String, UserCriteria> {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);
//
//    Page<UserDTO> getAllManagedUsers(Pageable pageable);

    Optional<User> getUserWithAuthoritiesByLogin();

    Optional<User> getUserWithAuthoritiesByLogin(String login);


}
