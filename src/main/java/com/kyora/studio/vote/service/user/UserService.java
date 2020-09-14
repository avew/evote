package com.kyora.studio.vote.service.user;

import com.kyora.studio.vote.domain.user.User;
import com.kyora.studio.vote.exception.UsernameExistsException;
import com.kyora.studio.vote.web.dto.user.UserCreateDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    User createUserWithAuthoritiesUser(UserCreateDTO user) throws UsernameExistsException;

    User updateUser(User user);
}
