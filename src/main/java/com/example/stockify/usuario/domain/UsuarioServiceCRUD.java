package com.example.stockify.usuario.domain;

import com.example.stockify.usuario.dto.UsuarioResponseDTO;
import com.example.stockify.usuario.infrastructure.UsuarioRepository;
import com.example.stockify.usuario.dto.UsuarioRequestDTO;
import com.example.stockify.usuario.dto.UsuarioNewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Service para Usuario con DTOs y ModelMapper.
@Service
public class UsuarioServiceCRUD {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioServiceCRUD(ModelMapper modelMapper, UsuarioRepository usuarioRepository) {
        this.modelMapper = modelMapper;
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioRequestDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, UsuarioRequestDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioRequestDTO findById(Long id) {
        Usuario e = usuarioRepository.findById(id).orElseThrow();
        return modelMapper.map(e, UsuarioRequestDTO.class);
    }

    public UsuarioResponseDTO create(UsuarioNewDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setActivo(true);
        usuario = usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioRequestDTO update(Long id, UsuarioNewDTO dto) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        modelMapper.map(dto, existing);
        existing = usuarioRepository.save(existing);
        return modelMapper.map(existing, UsuarioRequestDTO.class);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
