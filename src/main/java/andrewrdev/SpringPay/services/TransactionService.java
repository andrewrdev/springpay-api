package andrewrdev.SpringPay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import andrewrdev.SpringPay.dtos.TransactionDTO;
import andrewrdev.SpringPay.models.Transaction;
import andrewrdev.SpringPay.models.User;
import andrewrdev.SpringPay.repositories.TransactionRepository;
import andrewrdev.SpringPay.responses.AuthorizationResponse;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String AUTHORIZATION_SERVICE_URL = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

    // --------------------------------------------------------------------------------------------

    @Transactional
    public void createTransaction(TransactionDTO transactionDTO) {

        User sender = userService.getUserById(transactionDTO.getSenderId()).get();
        User receiver = userService.getUserById(transactionDTO.getReceiverId()).get();

        Transaction transaction = new Transaction();

        transaction.setAmount(transactionDTO.getAmount());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);

        sender.setBalance(sender.getBalance().subtract(transactionDTO.getAmount()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.getAmount()));       
     
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
        this.transactionRepository.save(transaction);
        this.notificationService.sendNotification(transaction, sender, receiver);
    }

    // --------------------------------------------------------------------------------------------

    public boolean isTransactionAuthorized() {
        ResponseEntity<AuthorizationResponse> response = restTemplate.getForEntity(AUTHORIZATION_SERVICE_URL,
                AuthorizationResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            AuthorizationResponse responseBody = response.getBody();

            return responseBody.getMessage().equalsIgnoreCase("Autorizado") ? true : false;
        } else {
            return false;
        }
    }

    // --------------------------------------------------------------------------------------------
}
