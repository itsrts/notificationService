package me.rajdeepdeb.notification.service.impl;

import me.rajdeepdeb.notification.model.Client;
import me.rajdeepdeb.notification.model.Subscription;
import me.rajdeepdeb.notification.objects.SubscriptionTypeEnum;
import me.rajdeepdeb.notification.service.ISubscriptionService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

    // taking map as an in-memory data store
    private HashMap<String, Client> subscriptions = new HashMap<>();

    public SubscriptionServiceImpl() {
        // ADDING TEST DATA FOR CLIENTS AND SUBSCRIPTION
        Client client = new Client("test1");
        addSubscription(client, SubscriptionTypeEnum.GOLD);
        System.out.println("client added with subscription : " + client.getSubscription().getUuid());
        System.out.println(client);

        client = new Client("test2");
        addSubscription(client, SubscriptionTypeEnum.SILVER);
        System.out.println("client added with subscription : " + client.getSubscription().getUuid());
        System.out.println(client);
    }

    @Override
    public Client getClientForToken(String token) {
        return subscriptions.get(token);
    }

    @Override
    public String addSubscription(Client client, SubscriptionTypeEnum type) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        String uuid = UUID.randomUUID().toString();
        Subscription subscription = new Subscription();
        subscription.setUuid(uuid);
        subscription.setType(type);
        subscription.setExpireAt(calendar.getTime());
        client.setSubscription(subscription);
        subscriptions.put(uuid, client);
        return uuid;
    }
}
