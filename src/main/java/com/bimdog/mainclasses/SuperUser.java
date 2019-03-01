package com.bimdog.mainclasses;

public class SuperUser extends User {

    public SuperUser(String name, String surname, String login, String password, String country) {
        super(name, surname, login, password, country);
    }

    public String show(){
        return null; //TODO реалізувати метод показу даних даного об'єкта
    }

    public String showAll(){
        return null; //TODO реалізувати метод показу всіх користувачів
    }

    public void update(){
        //TODO реалізувати метод редагування даних об'єкта користувача
    }

    public void delеte(){
        //TODO реалізувати метод видалення користувача
    }

    public void delеteAll(){
        //TODO реалізувати метод видалення користувача
    }
}
