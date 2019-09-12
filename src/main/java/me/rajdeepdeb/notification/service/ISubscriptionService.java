package me.rajdeepdeb.notification.service;

import me.rajdeepdeb.notification.model.Client;
import me.rajdeepdeb.notification.model.Subscription;
import me.rajdeepdeb.notification.objects.SubscriptionTypeEnum;

public interface ISubscriptionService {

    Client getClientForToken(String token);

    String addSubscription(Client client, SubscriptionTypeEnum type);
}
