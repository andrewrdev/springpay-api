package andrewrdev.SpringPay.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import andrewrdev.SpringPay.models.Notification;
import andrewrdev.SpringPay.services.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    // --------------------------------------------------------------------------------------------

    @GetMapping
    public ResponseEntity<List<Notification>> getAllUsers() {

        List<Notification> notifications = notificationService.getAll();      
        return new ResponseEntity<>(notifications, HttpStatus.OK);

    }

    // --------------------------------------------------------------------------------------------
}
