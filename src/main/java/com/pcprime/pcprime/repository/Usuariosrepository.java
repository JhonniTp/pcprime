package com.pcprime.pcprime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcprime.pcprime.model.UsuariosModel;

import java.util.Optional;


@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, Integer> {
    Optional<UsuariosModel> findByCorreo(String correo);
}
