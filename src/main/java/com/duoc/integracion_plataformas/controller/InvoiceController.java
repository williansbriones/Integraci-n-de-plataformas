package com.duoc.integracion_plataformas.controller;


import com.duoc.integracion_plataformas.dto.InvoiceDto;
import com.duoc.integracion_plataformas.dto.PreInvoice;
import com.duoc.integracion_plataformas.service.iface.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InvoiceController {

  private final InvoiceService invoiceService;

  @PostMapping("/create")
  public String createInvoice(@RequestBody PreInvoice PreInvoiceLessTotal) {
    PreInvoice preInvoice = PreInvoice.sumTotal(PreInvoiceLessTotal);
    return invoiceService.createInvoice(preInvoice);
  }

  @PostMapping("/confirm")
  public void confirmInvoice(@RequestParam("token") String token) {
    invoiceService.confirmInvoice(token);
  }
  @PostMapping("/listPaid")
  public List<InvoiceDto> listPaid() {
    return invoiceService.getListPaid();
  }
}
