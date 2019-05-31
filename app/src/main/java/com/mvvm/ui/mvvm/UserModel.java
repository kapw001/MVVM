package com.mvvm.ui.mvvm;

import java.util.ArrayList;

public class UserModel {

    private String title;

    private String message;


    public UserModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public UserModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
