package com.duoc.integracion_plataformas.repository;

import com.duoc.integracion_plataformas.entity.InvoiceEntity;
import com.duoc.integracion_plataformas.enums.StateInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

  Optional<InvoiceEntity> findByToken(String token);

  List<InvoiceEntity> findByStateInvoice(StateInvoice stateInvoice);

  List<InvoiceEntity> findByUser_Id(Long id);
}
