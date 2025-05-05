package com.pcprime.pcprime.service.impl;

import com.pcprime.pcprime.model.UsuarioModel;
import com.pcprime.pcprime.repository.UsuarioRepository;
import com.pcprime.pcprime.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioModel> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public UsuarioModel saveUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}