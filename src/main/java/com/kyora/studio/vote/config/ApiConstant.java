package com.kyora.studio.vote.config;

public class ApiConstant {

    public static final String API_V1 = "/api/v1";

    public interface USER {
        String ROOT = "/user";
    }

    public interface ACCOUNT {
        String ROOT = "/account";
        String ME = "/me";
    }

    public interface UPLOAD {
        String ROOT = "/upload";
        String IMAGE = "/image";
    }
}
