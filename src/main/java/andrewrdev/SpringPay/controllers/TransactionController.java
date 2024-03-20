package andrewrdev.SpringPay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import andrewrdev.SpringPay.dtos.TransactionDTO;
import andrewrdev.SpringPay.models.User;
import andrewrdev.SpringPay.responses.CustomResponse;
import andrewrdev.SpringPay.services.NotificationService;
import andrewrdev.SpringPay.services.TransactionService;
import andrewrdev.SpringPay.services.UserService;
import andrewrdev.SpringPay.validations.TransactionValidation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
@Validated
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TransactionValidation transactionValidation;

    // --------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {        

        if (!transactionValidation.isTransactionAuthorized()) {  
            CustomResponse response = new CustomResponse("transaction service unauthorized", "UNAUTHORIZED", 401);        
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        if (!notificationService.isNotificationAuthorized()) {  
            CustomResponse response = new CustomResponse("notification service unauthorized", "UNAUTHORIZED", 401);        
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        if (!transactionValidation.hasUserInDatabase(transactionDTO.getSenderId())) {
            CustomResponse response = new CustomResponse("sender id not exists", "NOT_FOUND", 404);        
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);        
        }

        if (!transactionValidation.hasUserInDatabase(transactionDTO.getReceiverId())) {
            CustomResponse response = new CustomResponse("receiver id not exists", "NOT_FOUND", 404);        
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);            
        }

        if (transactionValidation.isSenderAndReceiverSame(transactionDTO)) {
            CustomResponse response = new CustomResponse("sender and receiver cannot be the same", "BAD_REQUEST", 400);        
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (transactionValidation.isAmountLessThanMinimalValue(transactionDTO)) {
            CustomResponse response = new CustomResponse("the amount have be greater than 0.00", "BAD_REQUEST", 400);        
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User sender = userService.getUserById(transactionDTO.getSenderId()).get();

        if (transactionValidation.isSenderRetailer(sender)) {
            CustomResponse response = new CustomResponse("retailer user cannot send money", "BAD_REQUEST", 400);        
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (transactionValidation.hasSenderSufficientBalance(sender, transactionDTO.getAmount())) {
            CustomResponse response = new CustomResponse("sender does not have sufficient balance", "BAD_REQUEST", 400);        
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);            
        }

        try {
            transactionService.createTransaction(transactionDTO);
            return new ResponseEntity<>(new CustomResponse("transaction created with success", "CREATED", 201), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CustomResponse("error on create transaction", "INTERNAL_SERVER_ERROR", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       

    }

    // --------------------------------------------------------------------------------------------
}
