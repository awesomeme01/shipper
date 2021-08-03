package com.example.demo.helper;

public class PasswordChangeWrapper {
    private String oldPassword;
    private String securityCode;
    private String newPassword;

    public PasswordChangeWrapper() {
    }

    public PasswordChangeWrapper(String oldPassword, String securityCode, String newPassword) {
        this.oldPassword = oldPassword;
        this.securityCode = securityCode;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
