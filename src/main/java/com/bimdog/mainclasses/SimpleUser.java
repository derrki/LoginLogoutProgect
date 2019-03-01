package com.bimdog.mainclasses;

public class SimpleUser extends User {

    public SimpleUser(String name, String surname, String login, String password, String country) {
        super(name, surname, login, password, country);
    }

    public String show(){
        return null; //TODO реалізувати метод показу даних даного об'єкта
    }


    public void update(){
        //TODO реалізувати метод редагування даних об'єкта користувача
    }

    public void delеte(){
        //TODO реалізувати метод видалення користувача
    }

}
