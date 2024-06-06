package com.duoc.integracion_plataformas.entity;

import com.duoc.integracion_plataformas.enums.StateInvoice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice")
public class InvoiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull
  @Column(name = "date", nullable = false)
  private Instant date;

  @Size(max = 250)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "store")
  private String store;

  @NotNull
  @Column(name = "id_cashier")
  private Long idCashier;

  @NotNull
  @Column(name = "total")
  private Integer total;

  @Column(name = "token")
  private String token;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private UserEntity user;

  @Enumerated(EnumType.STRING)
  @Column(name = "state_invoice")
  private StateInvoice stateInvoice;


}
