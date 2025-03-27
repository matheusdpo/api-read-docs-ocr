package com.lumen.billing.v1.repositories;

import com.lumen.billing.v1.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByActiveFalseAndLastPaymentDateAfter(LocalDate date);

    List<UserEntity> findByLastPaymentDateBeforeOrLastPaymentDateIsNull(LocalDate date);

    List<UserEntity> findAllByActiveTrue();
}
