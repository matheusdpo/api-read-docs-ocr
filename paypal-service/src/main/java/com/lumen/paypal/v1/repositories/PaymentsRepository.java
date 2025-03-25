package com.lumen.paypal.v1.repositories;

import com.lumen.paypal.v1.entities.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentsRepository extends JpaRepository<PaymentsEntity, Long> {
    List<PaymentsEntity> findAllByUserId(Long userId);

    List<PaymentsEntity> findAllByPaymentStatus(String status);

    List<PaymentsEntity> findAllByCurrency(String currency);

    PaymentsEntity findByTransactionId(String transactionId);

    Optional<PaymentsEntity> findById(Long id);
}