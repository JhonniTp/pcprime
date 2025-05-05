package com.pcprime.pcprime.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pcprime.pcprime.model.ProductoModel;
import com.pcprime.pcprime.repository.ProductoRepository;
import com.pcprime.pcprime.service.ProductoService;

public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoModel> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoModel saveProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Optional<ProductoModel> getProductoById(Integer id) {
        return productoRepository.findById(id);
    }

}
