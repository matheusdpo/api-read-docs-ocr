package com.lumen.billing.v1.repositories;

import com.lumen.billing.v1.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceEntityRepository extends JpaRepository<InvoiceEntity, Long> {

    List<InvoiceEntity> findByIsPaidFalseAndDueDateBefore(LocalDate dueDate);

}
