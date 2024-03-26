package andrewrdev.SpringPay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import andrewrdev.SpringPay.models.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
}
