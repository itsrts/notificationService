package me.rajdeepdeb.notification.objects;

public enum NotificationStatusEnum {
    PENDING("PENDING"), SUCCESS("SUCCESS"), FAILED("FAILED");

    private String type;

    NotificationStatusEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
