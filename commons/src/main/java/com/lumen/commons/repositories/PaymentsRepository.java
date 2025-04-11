package com.lumen.commons.repositories;

import com.lumen.commons.models.entities.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentsRepository extends JpaRepository<PaymentsEntity, Long> {
    List<PaymentsEntity> findAllByUserId(Long userId);

    List<PaymentsEntity> findAllByPaymentStatus(String status);

    List<PaymentsEntity> findAllByCurrency(String currency);

    PaymentsEntity findByTransactionId(String transactionId);

    Optional<PaymentsEntity> findById(Long id);
}