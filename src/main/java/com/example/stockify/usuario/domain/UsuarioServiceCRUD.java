package com.example.stockify.usuario.domain;
import com.example.stockify.excepciones.BadRequestException;
import com.example.stockify.excepciones.ResourceNotFoundException;
import com.example.stockify.usuario.dto.UsuarioResponseDTO;
import com.example.stockify.usuario.infrastructure.UsuarioRepository;
import com.example.stockify.usuario.dto.UsuarioRequestDTO;
import com.example.stockify.usuario.dto.UsuarioNewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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

    public UsuarioRequestDTO updateFull(Long id, UsuarioNewDTO dto) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado"));

        if (dto.getNombre() == null || dto.getApellido() == null || dto.getEmail() == null ||
                dto.getTelefono() == null || dto.getRol() == null || dto.getSede() == null ||
                dto.getPassword() == null || dto.getActivo() == null) {
            throw new BadRequestException("Todos los campos son obligatorios para una actualización completa (PUT)");
        }

        modelMapper.map(dto, existing);
        existing = usuarioRepository.save(existing);
        return modelMapper.map(existing, UsuarioRequestDTO.class);
    }


    public UsuarioRequestDTO updatePartial(Long id, UsuarioNewDTO dto) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado"));

        if (dto.getNombre() != null) existing.setNombre(dto.getNombre());
        if (dto.getApellido() != null) existing.setApellido(dto.getApellido());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getTelefono() != null) existing.setTelefono(dto.getTelefono());
        if (dto.getRol() != null) existing.setRol(dto.getRol());
        if (dto.getSede() != null) existing.setSede(dto.getSede());
        if (dto.getPassword() != null) existing.setPassword(dto.getPassword());
        if (dto.getActivo() != null) existing.setActivo(dto.getActivo());

        existing = usuarioRepository.save(existing);
        return modelMapper.map(existing, UsuarioRequestDTO.class);
    }
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
