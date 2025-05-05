package com.pcprime.pcprime.service;

import com.pcprime.pcprime.model.UsuarioModel;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioModel> getAllUsuarios();
    Optional<UsuarioModel> getUsuarioById(Integer id);
    UsuarioModel saveUsuario(UsuarioModel usuario);
    void deleteUsuario(Integer id);
}