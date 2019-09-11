package me.rajdeepdeb.notification.service.impl;

import me.rajdeepdeb.notification.model.Notification;
import me.rajdeepdeb.notification.service.INotificationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificationServiceImpl implements INotificationService {

    @Override
    public String publishNotification(Notification notification) {
        System.out.println("publishing the notification : " + notification);
        return UUID.randomUUID().toString();
    }
}
