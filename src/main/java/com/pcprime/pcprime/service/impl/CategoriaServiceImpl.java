package com.pcprime.pcprime.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcprime.pcprime.model.CategoriaModel;
import com.pcprime.pcprime.repository.CategoriaRepository;
import com.pcprime.pcprime.service.CategoriaService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaModel crear(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaModel> obtenerPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaModel> listarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaModel actualizar(Integer id, CategoriaModel categoria) {
        return categoriaRepository.findById(id)
                .map(existing -> {
                    existing.setNombreCategoria(categoria.getNombreCategoria());
                    existing.setDescripcion(categoria.getDescripcion());
                    return categoriaRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @Override
    public void eliminar(Integer id) {
        categoriaRepository.deleteById(id);
    }
}