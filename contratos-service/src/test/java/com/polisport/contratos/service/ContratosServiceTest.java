package com.polisport.contratos.service;

import com.polisport.contratos.model.Contrato;
import com.polisport.contratos.model.EstadoContrato;
import com.polisport.contratos.model.TipoContrato;
import com.polisport.contratos.repository.ContratosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link ContratosService} usando Mockito puro
 * (sin levantar el contexto de Spring) para maximizar velocidad y
 * cobertura de linea sobre el paquete {@code service}.
 */
@ExtendWith(MockitoExtension.class)
class ContratosServiceTest {

    @Mock
    private ContratosRepository contratosRepository;

    @InjectMocks
    private ContratosService contratosService;

    private Contrato contrato;

    @BeforeEach
    void setUp() {
        contrato = new Contrato();
        contrato.setId(1L);
        contrato.setRunEmpleado(18765432);
        contrato.setDvrunEmpleado("5");
        contrato.setCargo("Entrenador");
        contrato.setFechaInicio(LocalDate.of(2025, 1, 15));
        contrato.setFechaTermino(LocalDate.of(2026, 1, 15));
        contrato.setSalarioMensual(2500000);
        contrato.setTipoContrato(TipoContrato.PLAZO_FIJO);
        contrato.setEstado(EstadoContrato.ACTIVO);
    }

    @Test
    void deberiaObtenerTodosCuandoHayContratosRegistrados() {
        when(contratosRepository.findAll()).thenReturn(List.of(contrato));

        List<Contrato> resultado = contratosService.obtenerTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Entrenador", resultado.get(0).getCargo());
        verify(contratosRepository, times(1)).findAll();
    }

    @Test
    void deberiaObtenerTodosVacioCuandoNoHayContratosRegistrados() {
        when(contratosRepository.findAll()).thenReturn(Collections.emptyList());

        List<Contrato> resultado = contratosService.obtenerTodos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(contratosRepository, times(1)).findAll();
    }

    @Test
    void deberiaObtenerPorIdCuandoElContratoExiste() {
        when(contratosRepository.findById(1L)).thenReturn(Optional.of(contrato));

        Optional<Contrato> resultado = contratosService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals(TipoContrato.PLAZO_FIJO, resultado.get().getTipoContrato());
    }

    @Test
    void deberiaRetornarOptionalVacioCuandoElContratoNoExiste() {
        when(contratosRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Contrato> resultado = contratosService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
        verify(contratosRepository, times(1)).findById(99L);
    }

    @Test
    void deberiaEliminarCuandoElContratoExiste() {
        doNothing().when(contratosRepository).deleteById(1L);

        contratosService.eliminar(1L);

        verify(contratosRepository, times(1)).deleteById(1L);
    }

    @Test
    void deberiaLanzarExcepcionAlEliminarCuandoElContratoNoExiste() {
        doThrow(new EmptyResultDataAccessException(1)).when(contratosRepository).deleteById(anyLong());

        assertThrows(EmptyResultDataAccessException.class, () -> contratosService.eliminar(99L));
        verify(contratosRepository, times(1)).deleteById(99L);
    }

    @Test
    void deberiaGuardarUnContratoNuevo() {
        when(contratosRepository.save(any(Contrato.class))).thenReturn(contrato);

        Contrato resultado = contratosService.guardar(contrato);

        assertNotNull(resultado);
        assertEquals(2500000, resultado.getSalarioMensual());
        assertEquals(EstadoContrato.ACTIVO, resultado.getEstado());
        verify(contratosRepository, times(1)).save(contrato);
    }

    @Test
    void deberiaActualizarUnContratoExistente() {
        contrato.setCargo("Recepcionista");
        when(contratosRepository.save(any(Contrato.class))).thenReturn(contrato);

        Contrato resultado = contratosService.guardar(contrato);

        assertNotNull(resultado);
        assertEquals("Recepcionista", resultado.getCargo());
    }

    @Test
    void deberiaListarPorEmpleadoCuandoTieneHistorialDeContratos() {
        when(contratosRepository.findByRunEmpleado(18765432)).thenReturn(List.of(contrato));

        List<Contrato> resultado = contratosService.listarPorEmpleado(18765432);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(18765432, resultado.get(0).getRunEmpleado());
    }

    @Test
    void deberiaListarPorEmpleadoVacioCuandoNoTieneContratos() {
        when(contratosRepository.findByRunEmpleado(11111111)).thenReturn(Collections.emptyList());

        List<Contrato> resultado = contratosService.listarPorEmpleado(11111111);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}
