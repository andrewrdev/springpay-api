package andrewrdev.SpringPay.dtos;

import java.math.BigDecimal;

import andrewrdev.SpringPay.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String document;
    private String email;    
    private String password;
    private UserType type;
    private BigDecimal balance;
}
