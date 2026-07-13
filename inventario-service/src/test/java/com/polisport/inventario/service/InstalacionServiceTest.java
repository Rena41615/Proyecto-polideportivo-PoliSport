package com.polisport.inventario.service;

import com.polisport.inventario.model.Instalacion;
import com.polisport.inventario.repository.InstalacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link InstalacionService}, verificando la correcta
 * delegacion de cada operacion sobre {@link InstalacionRepository}.
 */
@ExtendWith(MockitoExtension.class)
class InstalacionServiceTest {

    @Mock
    private InstalacionRepository instalacionRepository;

    @InjectMocks
    private InstalacionService instalacionService;

    private Instalacion instalacion;

    @BeforeEach
    void setUp() {
        instalacion = new Instalacion();
        instalacion.setId(1L);
        instalacion.setNombre("Cancha de Futbol Principal");
        instalacion.setDescripcion("Cancha de futbol 11 con iluminacion nocturna");
        instalacion.setUbicacion("Sector Norte del Complejo");
        instalacion.setTipo("Futbol");
        instalacion.setCapacidad(100);
        instalacion.setDisponible(true);
        instalacion.setEstado("OPERATIVA");
    }

    @Test
    void shouldListarInstalaciones() {
        when(instalacionRepository.findAll()).thenReturn(List.of(instalacion));

        List<Instalacion> resultado = instalacionService.listar();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Cancha de Futbol Principal", resultado.get(0).getNombre());
    }

    @Test
    void shouldListarInstalacionesVacio() {
        when(instalacionRepository.findAll()).thenReturn(List.of());

        List<Instalacion> resultado = instalacionService.listar();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void shouldBuscarPorIdCuandoExiste() {
        when(instalacionRepository.findById(1L)).thenReturn(Optional.of(instalacion));

        Optional<Instalacion> resultado = instalacionService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Futbol", resultado.get().getTipo());
    }

    @Test
    void shouldBuscarPorIdCuandoNoExiste() {
        when(instalacionRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Instalacion> resultado = instalacionService.buscarPorId(99L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void shouldCrearInstalacion() {
        when(instalacionRepository.save(any(Instalacion.class))).thenReturn(instalacion);

        Instalacion resultado = instalacionService.crear(instalacion);

        assertEquals(instalacion.getNombre(), resultado.getNombre());
        verify(instalacionRepository, times(1)).save(instalacion);
    }

}
