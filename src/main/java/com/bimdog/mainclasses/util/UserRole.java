package com.bimdog.mainclasses.util;

import com.bimdog.mainclasses.User;

public class UserRole {

    public static String setUserRole(User user){
        String role = null;

        if (user==null){
            role = "admin";
        }
        else {
            role = "user";
        }
        return role;
    }
}
