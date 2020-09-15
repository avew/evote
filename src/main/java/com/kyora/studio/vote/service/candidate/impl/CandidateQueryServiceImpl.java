package com.kyora.studio.vote.service.candidate.impl;

import com.kyora.studio.vote.domain.candidate.Candidate;
import com.kyora.studio.vote.repository.candidate.CandidateCriteria;
import com.kyora.studio.vote.repository.candidate.CandidateRepository;
import com.kyora.studio.vote.service.candidate.CandidateQueryService;
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
public class CandidateQueryServiceImpl implements CandidateQueryService {

    private final CandidateRepository candidateRepository;

    @Override
    public Optional<Candidate> findById(String s) {
        return candidateRepository.findById(s);
    }

    @Override
    public Page<Candidate> findByCriteriaWithPage(CandidateCriteria criteria, Pageable pageable) {
        log.debug("REQUEST FIND BY CRITERIA WITH PAGE, CRITERIA: {}, PAGEABLE: {}", criteria, pageable);
        if (criteria == null)
            criteria = new CandidateCriteria();
        Specification<Candidate> specification = createSpecification(criteria);
        return candidateRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Candidate> findOneByCriteria(CandidateCriteria criteria) {
        if (criteria == null)
            criteria = new CandidateCriteria();
        Specification<Candidate> specification = createSpecification(criteria);
        return candidateRepository.findOne(specification);
    }

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    private Specification<Candidate> createSpecification(CandidateCriteria criteria) {
        Specification<Candidate> specification = Specification.where(null);

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
