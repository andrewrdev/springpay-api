package andrewrdev.SpringPay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import andrewrdev.SpringPay.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
