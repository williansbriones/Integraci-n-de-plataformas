package com.duoc.integracion_plataformas.service;

import com.duoc.integracion_plataformas.dto.FlowResponseDto;
import com.duoc.integracion_plataformas.dto.InvoiceDto;
import com.duoc.integracion_plataformas.exeption.InternalException;
import com.duoc.integracion_plataformas.exeption.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.duoc.integracion_plataformas.dto.PreInvoice;
import com.duoc.integracion_plataformas.entity.InvoiceEntity;
import com.duoc.integracion_plataformas.entity.UserEntity;
import com.duoc.integracion_plataformas.repository.InvoiceRepository;
import com.duoc.integracion_plataformas.service.iface.FlowService;
import com.duoc.integracion_plataformas.service.iface.InvoiceService;
import com.duoc.integracion_plataformas.service.iface.UserService;
import com.duoc.integracion_plataformas.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.duoc.integracion_plataformas.enums.StateInvoice.PAID;
import static com.duoc.integracion_plataformas.enums.StateInvoice.PENDING;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImp implements InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final UserService userService;
  private final FlowService flowService;

  @Override
  @Transactional
  public String createInvoice(PreInvoice preInvoice) {

    UserDto userDto = userService.getUserById(preInvoice.idUser());

    FlowResponseDto flowRespose = flowService.CreateOrder(preInvoice.total(), userDto.email());

    InvoiceEntity invoiceEntity =
        InvoiceEntity.builder()
            .id(null)
            .date(preInvoice.DateShopping())
            .store("online")
            .idCashier(new UUID(0, 0).getMostSignificantBits())
            .total(preInvoice.total())
            .user(UserEntity.builder().id(preInvoice.idUser()).build())
            .stateInvoice(PENDING)
            .token(flowRespose.token())
            .build();

    invoiceEntity = invoiceRepository.save(invoiceEntity);

    return flowRespose.url() + "?token=" + flowRespose.token();
  }

  @Override
  public void confirmInvoice(String token) {
    InvoiceEntity invoiceEntity =
        invoiceRepository
            .findByToken(token)
            .orElseThrow(
                () -> new UserException("403", HttpStatus.BAD_REQUEST, "Invoice not found"));

    invoiceEntity.setStateInvoice(PAID);
    invoiceRepository.save(invoiceEntity);
  }

  @Override
  public List<InvoiceDto> getListPaid() {
    var invoiceList = invoiceRepository.findByStateInvoice(PAID)
            .stream()
        .map(
            invoiceEntity ->
                InvoiceDto.builder()
                    .id(invoiceEntity.getId())
                    .productos(null)
                    .fecha(Timestamp.from(invoiceEntity.getDate()))
                    .estado(invoiceEntity.getStateInvoice())
                    .total(invoiceEntity.getTotal())
                    .idCaja(invoiceEntity.getIdCashier())
                    .user(UserDto.builder().id(invoiceEntity.getUser().getId()).build())
                    .token(invoiceEntity.getToken())
                    .build())
        .toList();

    if(invoiceList.isEmpty()){
      throw new UserException("404", HttpStatus.NOT_FOUND, "No invoices found");
    };

    return invoiceList;
  }

  @Override
  public List<InvoiceDto> getListUnpaid(Long id) {
    return invoiceRepository.findByUser_Id(id).stream().map(
            invoiceEntity ->
                    InvoiceDto.builder()
                            .id(invoiceEntity.getId())
                            .productos(null)
                            .fecha(Timestamp.from(invoiceEntity.getDate()))
                            .estado(invoiceEntity.getStateInvoice())
                            .total(invoiceEntity.getTotal())
                            .idCaja(invoiceEntity.getIdCashier())
                            .user(UserDto.builder().id(invoiceEntity.getUser().getId()).build())
                            .token(invoiceEntity.getToken())
                            .build())
            .toList();
  }
}
