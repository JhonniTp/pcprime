package com.pcprime.pcprime.service;

import java.util.List;
import java.util.Optional;

import com.pcprime.pcprime.model.CategoriaModel;

public interface CategoriaService {
    List<CategoriaModel> getAllCategorias();
    Optional<CategoriaModel> getCategoriaById(Integer id);
    CategoriaModel saveCategoria(CategoriaModel categoria);
    void deleteCategoria(Integer id);
}
