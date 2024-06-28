package com.duoc.integracion_plataformas.repository;

import com.duoc.integracion_plataformas.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {}
