package me.rajdeepdeb.notification.objects;

public enum  NotificationTypeEnum {
    EMAIL("EMAIL"), SMS("SMS"), PUSH("PUSH");

    private String type;

    NotificationTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
