package com.example.stockify.reportes.domain;

import com.example.stockify.alertaStock.infrastructure.AlertaStockRepository;
import com.example.stockify.excepciones.ResourceNotFoundException;
import com.example.stockify.lote.infrastructure.LoteRepository;
import com.example.stockify.movimiento.infrastructure.MovimientoRepository;
import com.example.stockify.producto.infrastructure.ProductoRepository;
import com.example.stockify.valorizacionPeriodo.infrastructure.ValorizacionPeriodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService{

    private final ProductoRepository productoRepository;
    private final LoteRepository loteRepository;
    private final MovimientoRepository movimientoRepository;
    private final AlertaStockRepository alertaStockRepository;
    private final ValorizacionPeriodoRepository valorizacionPeriodoRepository;

    public ReporteService(ProductoRepository productoRepository,
                          LoteRepository loteRepository,
                          MovimientoRepository movimientoRepository,
                          AlertaStockRepository alertaStockRepository,
                          ValorizacionPeriodoRepository valorizacionPeriodoRepository) {
        this.productoRepository = productoRepository;
        this.loteRepository = loteRepository;
        this.movimientoRepository = movimientoRepository;
        this.alertaStockRepository = alertaStockRepository;
        this.valorizacionPeriodoRepository = valorizacionPeriodoRepository;
    }

    public Map<String, Object> generarResumenGeneral(String periodo) {
        Map<String, Object> data = new HashMap<>();

        double valorInventario = loteRepository.findAll().stream()
                .mapToDouble(l -> l.getCantidadDisponible() * l.getCostoUnitario())
                .sum();

        double costoVentas = valorizacionPeriodoRepository.findAll().stream()
                .filter(v -> v.getPeriodo().equals(periodo))
                .mapToDouble(v -> v.getCostoVentas())
                .sum();

        data.put("fechaReporte", LocalDate.now());
        if (valorizacionPeriodoRepository.findAll().stream()
                .noneMatch(v -> v.getPeriodo().equals(periodo))) {
            throw new ResourceNotFoundException("No existe valorización registrada para el periodo: " + periodo);
        }
        data.put("valorInventario", valorInventario);
        data.put("costoVentas", costoVentas);
        data.put("productos", productoRepository.findAll());
        data.put("lotes", loteRepository.findAll());
        data.put("movimientos", movimientoRepository.findAll());
        data.put("alertas", alertaStockRepository.findByAtendidoFalse());

        return data;
    }
}