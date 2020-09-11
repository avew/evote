package com.kyora.studio.vote.service.user;

import com.kyora.studio.vote.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by avew on 1/15/18.
 */
@Transactional(readOnly = true)
public interface UserQueryService {

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneByEmail(String email);

//    UserDTO findOneById(String userId);
//
//    Page<UserDTO> getAllManagedUsers(Pageable pageable);

    Optional<User> getUserWithAuthoritiesByLogin();

    Optional<User> getUserWithAuthoritiesByLogin(String login);

    User getUserWithAuthorities(Long id);

    User getUserWithAuthorities();

    List<String> getAuthorities();

//    List<UserDTO> findAllByCriteria(UserCriteria criteria);
//
//    Page<UserDTO> findByCriteria(UserCriteria criteria, Pageable page);
//
//    Page<UserDTO> findAllUserByCompanyId(String compId, UserCriteria criteria, Pageable pageable);
//
//    Page<UserDTO> findAllOwnerByCompanyId(String compId, UserCriteria criteria, Pageable pageable);
//
//    Set<UserDTO> findAllUserInCompanyByLoginAndCompanyId(String login, String companyId);
//
//    Optional<UserDTO> findTop1ByCompanyId(String companyId);

    int countById(String userId);

    String findUsernameById(String userId);

}
