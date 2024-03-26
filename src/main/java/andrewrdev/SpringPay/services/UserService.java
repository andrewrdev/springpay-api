package andrewrdev.SpringPay.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import andrewrdev.SpringPay.dtos.UserDTO;
import andrewrdev.SpringPay.models.User;
import andrewrdev.SpringPay.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    // --------------------------------------------------------------------------------------------

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // --------------------------------------------------------------------------------------------

    public Optional<User> getUserById(Long id) {        
        return repository.findById(id);
    }

    // --------------------------------------------------------------------------------------------

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);

        return repository.save(user);
    }  

    // --------------------------------------------------------------------------------------------
    
    public User saveUser(User user) {  
        return repository.save(user);
    }  

    // --------------------------------------------------------------------------------------------
    
    public boolean userExistsById(Long id) {  
        return repository.existsById(id);
    }  

    // --------------------------------------------------------------------------------------------

    public boolean userExistsByEmail(String email) {  
        return repository.existsByEmail(email);
    }  

    // --------------------------------------------------------------------------------------------

    public boolean userExistsByDocument(String document) {  
        return repository.existsByDocument(document);
    }  

    // --------------------------------------------------------------------------------------------
}
