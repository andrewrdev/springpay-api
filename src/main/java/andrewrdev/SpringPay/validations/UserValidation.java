package andrewrdev.SpringPay.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import andrewrdev.SpringPay.enums.UserType;
import andrewrdev.SpringPay.services.UserService;

@Component
public class UserValidation {

    @Autowired
    private UserService userService;   

    // --------------------------------------------------------------------------------------------

    public boolean hasEmailInDatabase(String email) {
        return userService.userExistsByEmail(email);
    }

    // --------------------------------------------------------------------------------------------

    public boolean hasDocumentInDatabase(String document) {
        return userService.userExistsByDocument(document);
    }

    // --------------------------------------------------------------------------------------------

    public boolean isValidType(String userType) {
        UserType userTypeEnum;
        try {
            userTypeEnum = UserType.valueOf(userType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
    
        return userTypeEnum == UserType.COMMON || userTypeEnum == UserType.RETAILER;
    }

    // --------------------------------------------------------------------------------------------
    
}
