package com.example.stockify.lote.domain;

import com.example.stockify.lote.infrastructure.LoteRepository;
import com.example.stockify.lote.dto.LoteRequestDTO;
import com.example.stockify.lote.dto.LoteNewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// Service para Lote con DTOs y ModelMapper.
@Service
public class LoteService {
    private final LoteRepository loteRepository;
    private final ModelMapper modelMapper;

    public LoteService(ModelMapper modelMapper, LoteRepository loteRepository) {
        this.modelMapper = modelMapper;
        this.loteRepository = loteRepository;
    }

    public List<LoteRequestDTO> findAll() {
        return loteRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, LoteRequestDTO.class))
                .collect(Collectors.toList());
    }

    public LoteRequestDTO findById(Long id) {
        Lote e = loteRepository.findById(id).orElseThrow();
        return modelMapper.map(e, LoteRequestDTO.class);
    }

    public LoteRequestDTO create(LoteNewDTO dto) {
        Lote e = modelMapper.map(dto, Lote.class);
        e = loteRepository.save(e);
        return modelMapper.map(e, LoteRequestDTO.class);
    }

    public LoteRequestDTO update(Long id, LoteRequestDTO dto) {
        Lote existing = loteRepository.findById(id).orElseThrow();
        modelMapper.map(dto, existing);
        existing = loteRepository.save(existing);
        return modelMapper.map(existing, LoteRequestDTO.class);
    }

    public void deleteById(Long id) {
        loteRepository.deleteById(id);
    }

    public List<Lote> obtenerLotesDisponiblesFIFO(Long productoId) {
        return loteRepository.findByProductoIdOrderByFechaCompraAsc(productoId);
    }
}
