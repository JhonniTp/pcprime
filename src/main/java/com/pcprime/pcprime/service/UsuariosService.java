package com.pcprime.pcprime.service;

import java.util.List;
import java.util.Optional;

import com.pcprime.pcprime.model.UsuariosModel;

public interface UsuariosService {
    UsuariosModel crear(UsuariosModel usuario);
    Optional<UsuariosModel> obtenerPorId(Integer id);
    List<UsuariosModel> listarTodos();
    UsuariosModel actualizar(Integer id, UsuariosModel usuario);
    void eliminar(Integer id);
}
