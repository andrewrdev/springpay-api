package andrewrdev.SpringPay.models;

import java.math.BigDecimal;
import andrewrdev.SpringPay.dtos.UserDTO;
import andrewrdev.SpringPay.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String fullName;
    @Column(unique = true)    
    private String document;
    @Column(unique = true)    
    private String email;       
    private String password;    
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)    
    private UserType userType;

    // --------------------------------------------------------------------------------------------

    public User(UserDTO userDTO) {

        this.setFullName(userDTO.getFullName());
        this.setDocument(userDTO.getDocument());
        this.setEmail(userDTO.getEmail());
        this.setPassword(userDTO.getPassword());
        this.setBalance(userDTO.getBalance());
        this.setUserType(userDTO.getUserType());
        
    }

    // --------------------------------------------------------------------------------------------
}
