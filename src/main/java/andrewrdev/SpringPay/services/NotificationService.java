package andrewrdev.SpringPay.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import andrewrdev.SpringPay.models.Notification;
import andrewrdev.SpringPay.models.Transaction;
import andrewrdev.SpringPay.models.User;
import andrewrdev.SpringPay.repositories.NotificationRepository;
import andrewrdev.SpringPay.responses.AuthorizationResponse;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String AUTHORIZATION_SERVICE_URL = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";

    // --------------------------------------------------------------------------------------------

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    // --------------------------------------------------------------------------------------------

    public void sendNotification(Transaction transaction, User sender, User receiver) { 
        Notification notification = new Notification();
        notification.setMessage("You received a transfer");
        notification.setAmount(transaction.getAmount());
        notification.setTransactionId(transaction.getId());
        notification.setReceiverId(transaction.getReceiver().getId());
        notification.setSenderid(transaction.getSender().getId());
        notification.setCreatedAt(LocalDateTime.now());
        
        notificationRepository.save(notification);
    }

    // --------------------------------------------------------------------------------------------

    public boolean isNotificationAuthorized() {
        ResponseEntity<AuthorizationResponse> response = restTemplate.getForEntity(AUTHORIZATION_SERVICE_URL,
                AuthorizationResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            AuthorizationResponse responseBody = response.getBody();

            return responseBody.getMessage().equalsIgnoreCase("true") ? true : false;
        } else {
            return false;
        }
    }

    // --------------------------------------------------------------------------------------------
}