package me.rajdeepdeb.notification.service.impl;

import me.rajdeepdeb.notification.exception.*;
import me.rajdeepdeb.notification.model.Client;
import me.rajdeepdeb.notification.model.Notification;
import me.rajdeepdeb.notification.objects.NotificationTypeEnum;
import me.rajdeepdeb.notification.service.INotificationService;
import me.rajdeepdeb.notification.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

@Service
public class NotificationServiceImpl implements INotificationService {

    // taking map as an in-memory data store, in actual it can be rabbitmq
    private HashMap<NotificationTypeEnum, ArrayBlockingQueue<Notification>> queue = new HashMap<>();

    // taking map as an in-memory data store, in actual it can be in sql / redis
    private HashMap<String, Notification> statusMap = new HashMap<>();

    // taking in-memory data structure for counting, in actual it can be in mysql with transactions / redis
    private HashMap<String, Integer> count = new HashMap<>();

    @Autowired
    ISubscriptionService subscriptionService;

    public NotificationServiceImpl() {
        queue.put(NotificationTypeEnum.SMS, new ArrayBlockingQueue<Notification>(100));
        queue.put(NotificationTypeEnum.EMAIL, new ArrayBlockingQueue<Notification>(100));
        queue.put(NotificationTypeEnum.PUSH, new ArrayBlockingQueue<Notification>(100));
    }

    @Override
    public String publishNotification(Notification notification, String token) throws SubscriptionExpiredException, SubscriptionUpgradationException, LimitReachedException, BadInputException {

        Client client = subscriptionService.getClientForToken(token);
        if (client.getSubscription().isExpired()) {
            throw new SubscriptionExpiredException();
        }
        // check if client is allowed for the notification type
        if (!client.getSubscription().getType().getTypes().contains(notification.getType())) {
            throw new SubscriptionUpgradationException();
        }
        System.out.println("publishing the notification : " + notification);
        String refId = UUID.randomUUID().toString();
        int limit = count.getOrDefault(client.getSubscription().getUuid(), 0);
        if (limit >= client.getSubscription().getType().getLimit()) {
            throw new LimitReachedException();
        }
        // check for data
        if(notification.getUser() == null || notification.getMessage() == null) {
            throw new BadInputException();
        }
        if(notification.getType() == NotificationTypeEnum.EMAIL && StringUtils.isEmpty(notification.getUser().getEmail())) {
            throw new BadInputException();
        }
        if(notification.getType() == NotificationTypeEnum.SMS && StringUtils.isEmpty(notification.getUser().getPhone())) {
            throw new BadInputException();
        }
        if(notification.getType() == NotificationTypeEnum.PUSH && StringUtils.isEmpty(notification.getUser().getUuid())) {
            throw new BadInputException();
        }
        // adding to queue
        queue.get(notification.getType()).add(notification);
        // notification status map
        statusMap.put(refId, notification);
        // client's notification count
        count.put(client.getSubscription().getUuid(), limit + 1);
        return refId;
    }

    @Override
    public Notification getStatus(String refId) throws Exception {
        Notification notification = statusMap.get(refId);
        if (notification == null)
            throw new NotFoundException();
        return notification;
    }
}
