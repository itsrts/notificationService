package me.rajdeepdeb.notification.model;

public class Client {

    private String name;
    private Subscription subscription;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", subscription=" + subscription +
                '}';
    }
}

