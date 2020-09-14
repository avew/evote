package com.kyora.studio.vote.service.user;

import com.kyora.studio.vote.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by avew on 1/15/18.
 */
@Transactional(readOnly = true)
public interface UserQueryService {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);
//
//    Page<UserDTO> getAllManagedUsers(Pageable pageable);

    Optional<User> getUserWithAuthoritiesByLogin();

    Optional<User> getUserWithAuthoritiesByLogin(String login);



}
