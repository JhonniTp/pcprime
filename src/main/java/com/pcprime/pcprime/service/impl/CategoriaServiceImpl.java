package com.pcprime.pcprime.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pcprime.pcprime.model.CategoriaModel;
import com.pcprime.pcprime.repository.CategoriaRepository;
import com.pcprime.pcprime.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    public List<CategoriaModel> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaModel saveCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Optional<CategoriaModel> getCategoriaById(Integer id) {
        return categoriaRepository.findById(id);
    }

}
