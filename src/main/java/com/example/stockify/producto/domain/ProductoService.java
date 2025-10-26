package com.example.stockify.producto.domain;

import com.example.stockify.producto.infrastructure.ProductoRepository;
import com.example.stockify.producto.dto.ProductoRequestDTO;
import com.example.stockify.producto.dto.ProductoNewDTO;
import com.example.stockify.excepciones.ResourceNotFoundException;
import com.example.stockify.excepciones.ValidacionException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    public ProductoService(ModelMapper modelMapper, ProductoRepository productoRepository) {
        this.modelMapper = modelMapper;
        this.productoRepository = productoRepository;
    }

    public List<ProductoRequestDTO> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, ProductoRequestDTO.class))
                .collect(Collectors.toList());
    }

    public ProductoRequestDTO findById(Long id) {
        Producto e = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        return modelMapper.map(e, ProductoRequestDTO.class);
    }

    public ProductoRequestDTO create(ProductoNewDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new ValidacionException("El nombre del producto es obligatorio.");
        }
        Producto e = modelMapper.map(dto, Producto.class);
        e = productoRepository.save(e);
        return modelMapper.map(e, ProductoRequestDTO.class);
    }

    public ProductoRequestDTO update(Long id, ProductoNewDTO dto) {
        Producto existing = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        modelMapper.map(dto, existing);
        existing = productoRepository.save(existing);
        return modelMapper.map(existing, ProductoRequestDTO.class);
    }

    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    @Transactional
    public void actualizarStock(Long productoId, double cantidadDelta) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + productoId));
        producto.setStockActual(producto.getStockActual() + cantidadDelta);
        productoRepository.save(producto);
    }
}