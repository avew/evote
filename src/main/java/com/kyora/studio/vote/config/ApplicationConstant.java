package com.kyora.studio.vote.config;

/**
 * Created by avew on 1/15/18.
 */
public final class ApplicationConstant {

    // Spring profiles for development, test and production, see http://www.jhipster.tech/profiles/
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    // Spring profile used when deploying with Spring Cloud (used when deploying to CloudFoundry)
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    // Spring profile used when deploying to Heroku
    public static final String SPRING_PROFILE_HEROKU = "heroku";
    // Spring profile used to disable swagger
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    // Spring profile used when deploying to Kubernetes and OpenShift
    public static final String SPRING_PROFILE_K8S = "k8s";

    public static final String TANPA_NPWP = "000000000000000";

    public ApplicationConstant() {
    }

}
