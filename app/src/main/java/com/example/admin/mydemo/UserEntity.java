package com.example.admin.mydemo;

/**
 * Created by admin on 2019/1/22.
 */

public class UserEntity {

    private final String userHome;
    private final int userAge;
    private final String userName;

    public UserEntity(String name, String home, int age) {
        this.userName = name;
        this.userAge = age;
        this.userHome = home;
    }

    public String getUserHome() {
        return userHome;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserName() {
        return userName;
    }
}
