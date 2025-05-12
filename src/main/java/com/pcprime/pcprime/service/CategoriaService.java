package com.pcprime.pcprime.service;


import com.pcprime.pcprime.model.CategoriaModel;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    CategoriaModel crear(CategoriaModel categoria);
    Optional<CategoriaModel> obtenerPorId(Integer id);
    List<CategoriaModel> listarTodos();
    CategoriaModel actualizar(Integer id, CategoriaModel categoria);
    void eliminar(Integer id);
}