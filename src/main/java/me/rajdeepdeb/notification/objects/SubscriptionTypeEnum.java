package me.rajdeepdeb.notification.objects;

import java.util.ArrayList;
import java.util.Arrays;

public enum SubscriptionTypeEnum {
    SILVER("SILVER", 1000000, 49, NotificationTypeEnum.EMAIL),
    GOLD("GOLD", 1000000, 99, NotificationTypeEnum.EMAIL, NotificationTypeEnum.SMS),
    PLATINUM("PLATINUM", -1, 500, NotificationTypeEnum.EMAIL, NotificationTypeEnum.SMS, NotificationTypeEnum.PUSH);

    private String type;
    private int limit;
    private float price;
    private ArrayList<NotificationTypeEnum> types;

    SubscriptionTypeEnum(String type, int limit, float price, NotificationTypeEnum... types) {
        this.type = type;
        this.limit = limit;
        this.price = price;
        this.types = new ArrayList<>();
        this.types.addAll(Arrays.asList(types));
    }

    public ArrayList<NotificationTypeEnum> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<NotificationTypeEnum> types) {
        this.types = types;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SubscriptionTypeEnum{" +
                "type='" + type + '\'' +
                ", limit=" + limit +
                ", price=" + price +
                ", types=" + types +
                '}';
    }
}
