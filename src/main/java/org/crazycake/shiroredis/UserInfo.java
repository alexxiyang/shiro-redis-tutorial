package org.crazycake.shiroredis;


import java.io.Serializable;

public class UserInfo implements Serializable{

    private String username;

    private Integer age;

    private String city;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
