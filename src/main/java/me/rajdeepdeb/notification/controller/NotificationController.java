package me.rajdeepdeb.notification.controller;

import me.rajdeepdeb.notification.constants.RequestHeaderConstants;
import me.rajdeepdeb.notification.exception.*;
import me.rajdeepdeb.notification.model.Notification;
import me.rajdeepdeb.notification.objects.NotificationRequestObject;
import me.rajdeepdeb.notification.objects.NotificationResponseObject;
import me.rajdeepdeb.notification.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity publishNotification(@RequestBody NotificationRequestObject body, @RequestHeader(RequestHeaderConstants.HEADER_AUTH) String token) {
        try {
            // publish also check for limit reached internally
            String refId = notificationService.publishNotification(body, token);
            return ResponseEntity.status(HttpStatus.OK).body(new NotificationResponseObject().setRefId(refId));
        } catch (LimitReachedException e) {
            return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(null);
        } catch (SubscriptionExpiredException e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
        } catch (SubscriptionUpgradationException | BadInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{refId}")
    public ResponseEntity notificationStatus(@PathVariable String refId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(notificationService.getStatus(refId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }
}
