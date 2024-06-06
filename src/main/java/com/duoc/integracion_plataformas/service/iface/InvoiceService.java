package com.duoc.integracion_plataformas.service.iface;

import com.duoc.integracion_plataformas.dto.InvoiceDto;
import com.duoc.integracion_plataformas.dto.PreInvoice;

import java.util.List;

public interface InvoiceService {

  String createInvoice(PreInvoice preInvoice);

  void confirmInvoice(String token);

  List<InvoiceDto> getListPaid();
}
