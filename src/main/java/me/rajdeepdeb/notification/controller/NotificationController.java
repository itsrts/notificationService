package me.rajdeepdeb.notification.controller;

import me.rajdeepdeb.notification.objects.NotificationRequestObject;
import me.rajdeepdeb.notification.objects.NotificationResponseObject;
import me.rajdeepdeb.notification.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<NotificationResponseObject> publishNotification(@RequestBody NotificationRequestObject body) {
        String refId = notificationService.publishNotification(body);
        return ResponseEntity.status(HttpStatus.OK).body(new NotificationResponseObject().setRefId(refId));
    }

    @RequestMapping(method = RequestMethod.GET)
    public void notificationDetails() {

    }
}
