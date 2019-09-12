package me.rajdeepdeb.notification.model;

import me.rajdeepdeb.notification.objects.NotificationTypeEnum;
import me.rajdeepdeb.notification.objects.SubscriptionTypeEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Subscription {

    private String uuid;
    private SubscriptionTypeEnum type;
    private Date expireAt;

    public boolean isExpired() {
        return new Date().after(expireAt);
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public Subscription() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public SubscriptionTypeEnum getType() {
        return type;
    }

    public void setType(SubscriptionTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "uuid=" + uuid +
                ", type=" + type +
                '}';
    }
}
