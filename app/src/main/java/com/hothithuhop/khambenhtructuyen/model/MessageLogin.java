package com.hothithuhop.khambenhtructuyen.model;

public class MessageLogin {
    int status;
    String notification;
    int id;

    public MessageLogin(int status, String notification, int id) {
        this.status = status;
        this.notification = notification;
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}