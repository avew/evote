package com.kyora.studio.vote.service.user.impl;

import com.kyora.studio.vote.domain.user.User;
import com.kyora.studio.vote.repository.UserRepository;
import com.kyora.studio.vote.security.SecurityUtils;
import com.kyora.studio.vote.service.user.UserCriteria;
import com.kyora.studio.vote.service.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findByCriteriaWithPage(UserCriteria criteria, Pageable pageable) {
        Specification<User> specification = createSpecification(criteria);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<User> findOneByCriteria(UserCriteria criteria) {
        Specification<User> specification = createSpecification(criteria);
        return userRepository.findOne(specification);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserWithAuthoritiesByLogin() {
        return userRepository.findOneWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin());
    }

    @Override
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    private Specification<User> createSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.where(null);

        if (criteria != null) {

//            if (criteria.getId() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getId(), UploadImage_.id));
//            }
//
//            if (criteria.getCategory() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getCategory(),
//                        UploadImage_.category));
//            }
//
//            if (criteria.getFilename() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getFilename(),
//                        UploadImage_.filename));
//            }
//
//            if (criteria.getOriginalFilename() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getOriginalFilename(),
//                        UploadImage_.originalFilename));
//            }
//
//            if (criteria.getNpwpProfile() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getNpwpProfile(),
//                        UploadImage_.npwpProfile));
//            }

        }

        return specification;
    }

}
