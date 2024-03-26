package andrewrdev.SpringPay.dtos;

import java.math.BigDecimal;
import andrewrdev.SpringPay.enums.UserType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
    @NotNull(message = "fullName cannot be null")
    private String fullName;    
   
    @NotNull(message = "fullName cannot be null")
    private String document;    
   
    @NotNull(message = "email cannot be null")
    @Email(message = "insert valid email")
    private String email;
    
    @NotNull(message = "password cannot be null")
    private String password;
    
    @NotNull(message = "balance cannot be null")
    @DecimalMin(value = "0.00", message = "minimal balance is 0.00")
    private BigDecimal balance;  

    @NotNull(message = "userType cannot be null")           
    private UserType userType;
    
}
