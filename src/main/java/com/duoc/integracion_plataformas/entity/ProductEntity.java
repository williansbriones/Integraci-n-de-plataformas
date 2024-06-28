package com.duoc.integracion_plataformas.entity;

import com.duoc.integracion_plataformas.enums.CategoryProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name_product")
  private String name;
  private String description;
  @Column(name = "Category_product")
  private CategoryProduct Category;
  private Integer price;
  private Integer stock;
  private Integer total;
  private String image;
}
