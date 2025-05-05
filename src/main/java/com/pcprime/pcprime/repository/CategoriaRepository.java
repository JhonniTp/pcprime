package com.pcprime.pcprime.repository;

import com.pcprime.pcprime.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer> {
}