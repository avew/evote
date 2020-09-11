package com.kyora.studio.vote.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

/*
 * Developed by Asep Rojali on 12/19/18 10:32 AM
 * Last modified 10/12/18 10:20 AM
 * Copyright (c) 2018. All rights reserved.
 */

@Configuration
public class JacksonConfiguration {

    /*
     * Support for Hibernate types in Jackson.
     */
//    @Bean
//    public Hibernate5Module hibernate5Module() {
//        return new Hibernate5Module();
//    }
//
//    /*
//     * Jackson Afterburner module to speed up serialization/deserialization.
//     */
//    @Bean
//    public AfterburnerModule afterburnerModule() {
//        return new AfterburnerModule();
//    }

    /*
     * Module for serialization/deserialization of RFC7807 Problem.
     */
    @Bean
    public ProblemModule problemModule() {
        return new ProblemModule();
    }


    @Bean
    public Module dateTimeModule() {
        return new JavaTimeModule();
    }

    /*
     * Module for serialization/deserialization of ConstraintViolationProblem.
     */
    @Bean
    public ConstraintViolationProblemModule constraintViolationProblemModule() {
        return new ConstraintViolationProblemModule();
    }
}
