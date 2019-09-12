package me.rajdeepdeb.notification.service;

import me.rajdeepdeb.notification.exception.BadInputException;
import me.rajdeepdeb.notification.model.Notification;

public interface INotificationService {

    String publishNotification(Notification notification, String token) throws Exception, BadInputException;

    Notification getStatus(String refId) throws Exception;
}
