package andrewrdev.SpringPay.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {  

    @NotNull(message = "amount cannot be null")
    private BigDecimal amount; 
    
    @NotNull(message = "senderId cannot be null")
    private Long senderId;  
    
    @NotNull(message = "receiverId cannot be null")
    private Long receiverId;  
}
