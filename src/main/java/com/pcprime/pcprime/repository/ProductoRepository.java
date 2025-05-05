package com.pcprime.pcprime.repository;

import com.pcprime.pcprime.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoModel, Integer> {
}