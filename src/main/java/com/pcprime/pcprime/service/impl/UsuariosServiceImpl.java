package com.pcprime.pcprime.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.pcprime.pcprime.model.UsuariosModel;
import com.pcprime.pcprime.repository.UsuariosRepository;
import com.pcprime.pcprime.service.UsuariosService;


@Service
@Transactional
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UsuariosModel crear(UsuariosModel usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuariosModel> obtenerPorId(Integer id) {
        return usuariosRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuariosModel> listarTodos() {
        return usuariosRepository.findAll();
    }

    @Override
    public UsuariosModel actualizar(Integer id, UsuariosModel usuario) {
        return usuariosRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(usuario.getNombre());
                    existing.setApellido(usuario.getApellido());
                    existing.setTelefono(usuario.getTelefono());
                    existing.setDni(usuario.getDni());
                    existing.setCorreo(usuario.getCorreo());
                    existing.setRol(usuario.getRol());
                    existing.setEstado(usuario.getEstado());
                    if (usuario.getContraseñaHash() != null && !usuario.getContraseñaHash().isEmpty()) {
                        existing.setContraseñaHash(usuario.getContraseñaHash());
                    }
                    return usuariosRepository.save(existing);
                })
                .orElseGet(() -> {
                    usuario.setId(id);
                    return usuariosRepository.save(usuario);
                });
    }

    @Override
    public void eliminar(Integer id) {
        usuariosRepository.deleteById(id);
    }
}
