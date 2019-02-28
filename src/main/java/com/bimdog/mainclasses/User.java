package com.bimdog.mainclasses;

public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String country;

    public User(String name, String surname, String login, String password, String country) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }
}
