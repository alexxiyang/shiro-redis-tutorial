package org.crazycake.shiroredis;


import java.io.Serializable;

public class AuthenticInfoExample implements Serializable{

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
