package com.pcprime.pcprime.service;

import java.util.List;
import java.util.Optional;

import com.pcprime.pcprime.model.VendedoresModel;

public interface VendedoresService {
    VendedoresModel crear(VendedoresModel vendedor);
    Optional<VendedoresModel> obtenerPorId(Integer id);
    List<VendedoresModel> listarTodos();
    VendedoresModel actualizar(Integer id, VendedoresModel vendedor);
    void eliminar(Integer id);
}
