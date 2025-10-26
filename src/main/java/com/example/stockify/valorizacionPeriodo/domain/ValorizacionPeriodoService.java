package com.example.stockify.valorizacionPeriodo.domain;

import com.example.stockify.excepciones.ResourceNotFoundException;
import com.example.stockify.lote.infrastructure.LoteRepository;
import com.example.stockify.movimiento.domain.Movimiento;
import com.example.stockify.movimiento.domain.TipoMovimiento;
import com.example.stockify.movimiento.infrastructure.MovimientoRepository;
import com.example.stockify.usuario.infrastructure.UsuarioRepository;
import com.example.stockify.valorizacionPeriodo.infrastructure.ValorizacionPeriodoRepository;
import com.example.stockify.valorizacionPeriodo.dto.ValorizacionPeriodoRequestDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValorizacionPeriodoService {

    private final ValorizacionPeriodoRepository valorizacionPeriodoRepository;
    private final LoteRepository loteRepository;
    private final MovimientoRepository movimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public ValorizacionPeriodoService(ValorizacionPeriodoRepository valorizacionPeriodoRepository,
                                      LoteRepository loteRepository,
                                      MovimientoRepository movimientoRepository,
                                      UsuarioRepository usuarioRepository,
                                      ModelMapper modelMapper) {
        this.valorizacionPeriodoRepository = valorizacionPeriodoRepository;
        this.loteRepository = loteRepository;
        this.movimientoRepository = movimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<ValorizacionPeriodoRequestDTO> findAll() {
        return valorizacionPeriodoRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, ValorizacionPeriodoRequestDTO.class))
                .collect(Collectors.toList());
    }

    public ValorizacionPeriodoRequestDTO findById(Integer id) {
        ValorizacionPeriodo e = valorizacionPeriodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Valorización no encontrada con ID: " + id));
        return modelMapper.map(e, ValorizacionPeriodoRequestDTO.class);
    }

    @Transactional
    public ValorizacionPeriodoRequestDTO ejecutarValorizacion(String periodo, MetodoValorizacion metodo, Long usuarioId) {
        YearMonth ym = YearMonth.parse(periodo);
        LocalDateTime inicio = ym.atDay(1).atStartOfDay();
        LocalDateTime fin = ym.atEndOfMonth().atTime(23, 59, 59);

        // 🔹 Calcular valor de inventario
        double valorInventario = loteRepository.findAll().stream()
                .mapToDouble(l -> l.getCantidadDisponible() * l.getCostoUnitario())
                .sum();

        // 🔹 Calcular costo de ventas
        double costoVentas = movimientoRepository.findAll().stream()
                .filter(m -> m.getTipoMovimiento() == TipoMovimiento.SALIDA)
                .filter(m -> m.getFechaMovimiento() != null &&
                        m.getFechaMovimiento().isAfter(inicio) &&
                        m.getFechaMovimiento().isBefore(fin))
                .mapToDouble(Movimiento::getCostoTotal)
                .sum();

        ValorizacionPeriodo valorizacion = new ValorizacionPeriodo();
        valorizacion.setPeriodo(periodo);
        valorizacion.setMetodoValorizacion(metodo);
        valorizacion.setValorInventario(valorInventario);
        valorizacion.setCostoVentas(costoVentas);
        valorizacion.setObservaciones("Cierre automático del periodo " + periodo);
        valorizacion.setUsuario(usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId)));
        valorizacion.setFechaValorizacion(LocalDateTime.now());

        valorizacionPeriodoRepository.save(valorizacion);
        return modelMapper.map(valorizacion, ValorizacionPeriodoRequestDTO.class);
    }
}