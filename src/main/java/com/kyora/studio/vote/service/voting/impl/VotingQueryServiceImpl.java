package com.kyora.studio.vote.service.voting.impl;

import com.kyora.studio.vote.domain.voting.Voting;
import com.kyora.studio.vote.repository.voting.VotingRepository;
import com.kyora.studio.vote.service.voting.VotingCriteria;
import com.kyora.studio.vote.service.voting.VotingQueryService;
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
public class VotingQueryServiceImpl implements VotingQueryService {

    private final VotingRepository votingRepository;

    @Override
    public Optional<Voting> findById(String s) {
        return votingRepository.findById(s);
    }

    @Override
    public Page<Voting> findByCriteriaWithPage(VotingCriteria criteria, Pageable pageable) {
        log.debug("REQUEST FIND BY CRITERIA WITH PAGE, CRITERIA: {}, PAGEABLE: {}", criteria, pageable);
        if (criteria == null)
            criteria = new VotingCriteria();
        Specification<Voting> specification = createSpecification(criteria);
        return votingRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Voting> findOneByCriteria(VotingCriteria criteria) {
        if (criteria == null)
            criteria = new VotingCriteria();
        Specification<Voting> specification = createSpecification(criteria);
        return votingRepository.findOne(specification);
    }

    @Override
    public List<Voting> findAll() {
        return votingRepository.findAll();
    }

    private Specification<Voting> createSpecification(VotingCriteria criteria) {
        Specification<Voting> specification = Specification.where(null);

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

    @Override
    public Optional<Voting> findByCandidateIdAndUserId(String candidateId, String userId) {
        return votingRepository.findByCandidateIdAndUserId(candidateId, userId);
    }
}
