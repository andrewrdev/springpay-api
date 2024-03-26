package andrewrdev.SpringPay.validations;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import andrewrdev.SpringPay.dtos.TransactionDTO;
import andrewrdev.SpringPay.enums.UserType;
import andrewrdev.SpringPay.models.User;
import andrewrdev.SpringPay.services.TransactionService;
import andrewrdev.SpringPay.services.UserService;

@Component
public class TransactionValidation {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService; 

    // --------------------------------------------------------------------------------------------
   
    public boolean isTransactionAuthorized() {
        return transactionService.isTransactionAuthorized();
    }

    // --------------------------------------------------------------------------------------------

    public boolean hasUserInDatabase(Long userId) {
        return userService.userExistsById(userId);
    }

    // --------------------------------------------------------------------------------------------

    public boolean isSenderAndReceiverSame(TransactionDTO transactionDTO) {
        return transactionDTO.getSenderId() == transactionDTO.getReceiverId();
    }

    // --------------------------------------------------------------------------------------------

    public boolean isSenderRetailer(User sender) {
        return sender.getUserType().equals(UserType.RETAILER);
    }

    // --------------------------------------------------------------------------------------------

    public boolean hasSenderSufficientBalance(User sender, BigDecimal amount) {
        return sender.getBalance().compareTo(amount) < 0;
    }

    // --------------------------------------------------------------------------------------------

    public boolean isAmountLessThanMinimalValue(TransactionDTO transactionDTO) {
        return transactionDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0;
    }

    // --------------------------------------------------------------------------------------------
}
