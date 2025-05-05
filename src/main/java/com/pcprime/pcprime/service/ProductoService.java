package com.pcprime.pcprime.service;
import com.pcprime.pcprime.model.ProductoModel;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<ProductoModel> getAllProductos();
    Optional<ProductoModel> getProductoById(Integer id);
    ProductoModel saveProducto(ProductoModel producto);
    void deleteProducto(Integer id);
}
