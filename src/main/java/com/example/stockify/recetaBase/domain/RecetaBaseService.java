package com.example.stockify.recetaBase.domain;

import com.example.stockify.excepciones.ResourceNotFoundException;
import com.example.stockify.recetaBase.infrastructure.RecetaBaseRepository;
import com.example.stockify.recetaBase.dto.RecetaBaseRequestDTO;
import com.example.stockify.recetaBase.dto.RecetaBaseNewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecetaBaseService {
    private final RecetaBaseRepository recetaBaseRepository;
    private final ModelMapper modelMapper;

    public RecetaBaseService(ModelMapper modelMapper, RecetaBaseRepository recetaBaseRepository) {
        this.modelMapper = modelMapper;
        this.recetaBaseRepository = recetaBaseRepository;
    }

    public List<RecetaBaseRequestDTO> findAll() {
        return recetaBaseRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, RecetaBaseRequestDTO.class))
                .collect(Collectors.toList());
    }

    public RecetaBaseRequestDTO findById(Long id) {
        RecetaBase receta = recetaBaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta base no encontrada con ID: " + id));
        return modelMapper.map(receta, RecetaBaseRequestDTO.class);
    }

    public RecetaBaseRequestDTO create(RecetaBaseNewDTO dto) {
        RecetaBase receta = modelMapper.map(dto, RecetaBase.class);
        receta.setActivo(true);
        receta.setFechaCreacion(java.time.LocalDateTime.now());
        receta = recetaBaseRepository.save(receta);
        return modelMapper.map(receta, RecetaBaseRequestDTO.class);
    }

    public RecetaBaseRequestDTO update(Long id, RecetaBaseNewDTO dto) {
        RecetaBase existing = recetaBaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta base no encontrada con ID: " + id));
        modelMapper.map(dto, existing);
        existing = recetaBaseRepository.save(existing);
        return modelMapper.map(existing, RecetaBaseRequestDTO.class);
    }

    public void deleteById(Long id) {
        if (!recetaBaseRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Receta base no encontrada con ID: " + id);
        }
        recetaBaseRepository.deleteById(id);
    }
}
