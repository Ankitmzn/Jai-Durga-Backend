package com.car.detailing.repository;

import com.car.detailing.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Additional custom query methods can be added here if needed
}
