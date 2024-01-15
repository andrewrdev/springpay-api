package andrewrdev.SpringPay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrewrdev.SpringPay.models.User;
import andrewrdev.SpringPay.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }  

    public void save(User user) {
        this.userRepository.save(user);
    } 
}
