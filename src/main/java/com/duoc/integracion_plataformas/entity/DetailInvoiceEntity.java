package com.duoc.integracion_plataformas.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detail_invoice")
public class DetailInvoiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "id_product")
  private Long Product;

  @Column(name = "count")
  private Integer count;

  @JoinColumn(name = "id_invoice")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private InvoiceEntity invoiceEntity;

}
