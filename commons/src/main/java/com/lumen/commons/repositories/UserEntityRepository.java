package com.lumen.commons.repositories;

import com.lumen.commons.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByActiveFalseAndLastPaymentDateAfter(LocalDate date);

    List<UserEntity> findByLastPaymentDateBeforeOrLastPaymentDateIsNull(LocalDate date);

    List<UserEntity> findAllByActiveTrue();

    Optional<UserEntity> findByUserName(String userName);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByPhone(String phone);
}
