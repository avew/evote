package com.kyora.studio.vote.web.vm;

import com.kyora.studio.vote.web.dto.user.UserCreateDTO;

import javax.validation.constraints.Size;

/**
 * Created by avew on 1/15/18.
 */
public class ManagedUserVM extends UserCreateDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
                "} " + super.toString();
    }
}
