package com.example.stockify.almacen.domain;

import com.example.stockify.almacen.infrastructure.AlmacenRepository;
import com.example.stockify.almacen.dto.AlmacenRequestDTO;
import com.example.stockify.almacen.dto.AlmacenNewDTO;
import com.example.stockify.excepciones.ResourceNotFoundException;
import com.example.stockify.excepciones.ValidacionException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlmacenService {

    private final AlmacenRepository almacenRepository;
    private final ModelMapper modelMapper;

    public AlmacenService(ModelMapper modelMapper, AlmacenRepository almacenRepository) {
        this.modelMapper = modelMapper;
        this.almacenRepository = almacenRepository;
    }

    public List<AlmacenRequestDTO> findAll() {
        return almacenRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, AlmacenRequestDTO.class))
                .collect(Collectors.toList());
    }

    public AlmacenRequestDTO findById(Long id) {
        Almacen e = almacenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Almacén no encontrado con ID: " + id));
        return modelMapper.map(e, AlmacenRequestDTO.class);
    }

    public AlmacenRequestDTO create(AlmacenNewDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new ValidacionException("El nombre del almacén es obligatorio.");
        }

        Almacen e = modelMapper.map(dto, Almacen.class);
        e = almacenRepository.save(e);

        return modelMapper.map(e, AlmacenRequestDTO.class);
    }

    public AlmacenRequestDTO update(Long id, AlmacenNewDTO dto) {
        Almacen existing = almacenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Almacén no encontrado con ID: " + id));

        modelMapper.map(dto, existing);
        existing = almacenRepository.save(existing);

        return modelMapper.map(existing, AlmacenRequestDTO.class);
    }

    public void deleteById(Long id) {
        if (!almacenRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Almacén no encontrado con ID: " + id);
        }
        almacenRepository.deleteById(id);
    }
}