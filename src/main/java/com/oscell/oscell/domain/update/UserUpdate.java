package com.oscell.oscell.domain.update;

public class UserUpdate {
    private Long sequence;
    private String userName;
    private String userPassword;

    public Long getSequence() {
        return sequence;
    }
    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
