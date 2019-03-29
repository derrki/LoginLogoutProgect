package com.bimdog.mainclasses.util;

public class UserRole {

    public static String setUserRole(boolean value){
        String role = null;
        if (value==false){
            role = "admin";
        }
        else {
            role = "user";
        }
        return role;
    }
}