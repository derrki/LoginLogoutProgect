package com.bimdog.mainclasses.util;

public class Validation {

    public static String validationFormData(String login, String password){

        String errorMsg = null;

        if(login == null || login.equals("")){
            System.out.println(" login =" +login);
            errorMsg = "Login can't be null or empty.";
        }
        if(password == null || password.equals("")){
            System.out.println("login");
            errorMsg = "Password can't be null or empty.";
        }
        return errorMsg;
    }

    public static String validationFormData(String name, String surname, String login, String password, String country){

        String errorMsg = null;

        if (name == null || name.equals("")) {
            errorMsg = "Name can't be null or empty.";
        }
        if (surname == null || surname.equals("")) {
            errorMsg = "Surname can't be null or empty.";
        }
        if (login == null || login.equals("")) {
            errorMsg = "Login can't be null or empty.";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty.";
        }
        if (country == null || country.equals("")) {
            errorMsg = "Country can't be null or empty.";
        }
        return errorMsg;
    }

}
