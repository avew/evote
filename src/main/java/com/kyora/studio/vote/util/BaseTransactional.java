package com.kyora.studio.vote.util;

/**
 * Created by avew on 1/17/18.
 */

/*
 * Developed by Asep Rojali on 12/20/18 1:25 PM
 * Last modified 12/18/18 7:25 PM
 * Copyright (c) 2018. All rights reserved.
 */

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseTransactional<D, ID> {

    D save(D dto) ;

    D update(D dto);

    void deleteById(ID id);

}
