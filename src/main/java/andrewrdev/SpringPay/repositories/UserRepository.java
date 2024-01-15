package andrewrdev.SpringPay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import andrewrdev.SpringPay.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
