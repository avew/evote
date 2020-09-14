package com.kyora.studio.vote.service.upload.image.impl;

import com.kyora.studio.vote.config.query.QueryService;
import com.kyora.studio.vote.domain.upload.image.UploadImage;
import com.kyora.studio.vote.repository.upload.image.UploadImageRepository;
import com.kyora.studio.vote.service.upload.image.UploadImageCriteria;
import com.kyora.studio.vote.service.upload.image.UploadImageQueryService;
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
public class UploadImageQueryServiceImpl extends QueryService<UploadImage> implements UploadImageQueryService {

    private final UploadImageRepository uploadImageRepository;

    @Override
    public Optional<UploadImage> findById(String s) {
        return uploadImageRepository.findById(s);
    }

    @Override
    public Page<UploadImage> findByCriteriaWithPage(UploadImageCriteria criteria, Pageable pageable) {
        log.debug("REQUEST FIND BY CRITERIA WITH PAGE, CRITERIA: {}, PAGEABLE: {}", criteria, pageable);
        if (criteria == null)
            criteria = new UploadImageCriteria();
        Specification<UploadImage> specification = createSpecification(criteria);
        return uploadImageRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<UploadImage> findOneByCriteria(UploadImageCriteria criteria) {
        if (criteria == null)
            criteria = new UploadImageCriteria();
        Specification<UploadImage> specification = createSpecification(criteria);
        return uploadImageRepository.findOne(specification);
    }

    @Override
    public List<UploadImage> findAll() {
        return uploadImageRepository.findAll();
    }

    private Specification<UploadImage> createSpecification(UploadImageCriteria criteria) {
        Specification<UploadImage> specification = Specification.where(null);

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
