package com.ems.Web.dto;


import com.ems.Validator.ValidPassword;

public class PasswordDTO {

    private String email;

    private String oldPassword;

    @ValidPassword
    private String newPassword;


    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email;}

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
