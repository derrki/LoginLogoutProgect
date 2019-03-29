package com.bimdog.mainclasses;

public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String country;
    private String role;

    public User(){}

    public User(String name, String surname, String login, String password, String country) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.country = country;
    }

    public User(String name, String surname, String login, String password, String country, String role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.country = country;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
