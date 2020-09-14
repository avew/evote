package com.kyora.studio.vote.util;

/*
 * Developed by Asep Rojali on 12/20/18 2:05 PM
 * Last modified 12/20/18 1:31 PM
 * Copyright (c) 2018. All rights reserved.
 */


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface BaseNonTransactional<D, ID, C> {


    Optional<D> findById(ID id);

    Page<D> findByCriteriaWithPage(C criteria, Pageable pageable);

    Optional<D> findOneByCriteria(C criteria);

    List<D> findAll();

}
