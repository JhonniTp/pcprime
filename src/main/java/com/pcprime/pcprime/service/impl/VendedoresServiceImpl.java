package com.pcprime.pcprime.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pcprime.pcprime.model.VendedoresModel;
import com.pcprime.pcprime.repository.VendedoresRepository;
import com.pcprime.pcprime.service.VendedoresService;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VendedoresServiceImpl implements VendedoresService {

    private final VendedoresRepository vendedoresRepository;

    public VendedoresServiceImpl(VendedoresRepository vendedoresRepository) {
        this.vendedoresRepository = vendedoresRepository;
    }

    @Override
    public VendedoresModel crear(VendedoresModel vendedor) {
        return vendedoresRepository.save(vendedor);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VendedoresModel> obtenerPorId(Integer id) {
        return vendedoresRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VendedoresModel> listarTodos() {
        return vendedoresRepository.findAll();
    }

    @Override
    public VendedoresModel actualizar(Integer id, VendedoresModel vendedor) {
        return vendedoresRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(vendedor.getNombre());
                    existing.setApellido(vendedor.getApellido());
                    existing.setTelefono(vendedor.getTelefono());
                    existing.setDni(vendedor.getDni());
                    existing.setCodigoVendedor(vendedor.getCodigoVendedor());
                    existing.setCorreo(vendedor.getCorreo());
                    existing.setFechaContratacion(vendedor.getFechaContratacion());
                    existing.setFechaFinContrato(vendedor.getFechaFinContrato());
                    existing.setSalario(vendedor.getSalario());
                    existing.setNotas(vendedor.getNotas());
                    existing.setEstado(vendedor.getEstado());
                    return vendedoresRepository.save(existing);
                })
                .orElseGet(() -> {
                    vendedor.setId(id);
                    return vendedoresRepository.save(vendedor);
                });
    }

    @Override
    public void eliminar(Integer id) {
        vendedoresRepository.deleteById(id);
    }
}