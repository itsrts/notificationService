package me.rajdeepdeb.notification.model;

import me.rajdeepdeb.notification.objects.NotificationStatusEnum;
import me.rajdeepdeb.notification.objects.NotificationTypeEnum;

public class Notification {

    private String message;
    private User user;
    private NotificationTypeEnum type;
    private NotificationStatusEnum status;

    public Notification() {
        this.status = NotificationStatusEnum.PENDING;
    }

    public NotificationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(NotificationStatusEnum status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationTypeEnum getType() {
        return type;
    }

    public void setType(NotificationTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "message='" + message + '\'' +
                ", user=" + user.toString() +
                ", type=" + type +
                '}';
    }
}
