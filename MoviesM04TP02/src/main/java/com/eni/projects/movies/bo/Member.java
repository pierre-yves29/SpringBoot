package com.eni.projects.movies.bo;

/**
 * Model for a member
 * @Version 1.0
 * @author qprigent
 */
public class Member extends Person {
    private String username;
    private String password;
    private boolean isAdmin = false;

    public Member() {
        super();
    }

    public Member(String lastName, String firstName, String username, String password, boolean isAdmin) {
        super(lastName, firstName);
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Member(long id, String lastName, String firstName, String username, String password, boolean isAdmin) {
        super(id, lastName, firstName);
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Member{");
        sb.append("username='").append(username).append('\'');
        sb.append(", isAdmin=").append(isAdmin);
        sb.append('}');
        return sb.toString();
    }
}
