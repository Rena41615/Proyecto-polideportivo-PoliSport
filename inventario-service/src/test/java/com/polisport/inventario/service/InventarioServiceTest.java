package com.polisport.inventario.service;

import com.polisport.inventario.model.Inventario;
import com.polisport.inventario.repository.InventarioRepository;
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
 * Pruebas unitarias de {@link InventarioService}, verificando la correcta
 * delegacion de cada operacion sobre {@link InventarioRepository}.
 */
@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    private Inventario equipo;

    @BeforeEach
    void setUp() {
        equipo = new Inventario();
        equipo.setId(1L);
        equipo.setNombre("Balon de futbol");
        equipo.setDescripcion("Balon oficial FIFA tamano 5");
        equipo.setUbicacion("Bodega B, Estante 3");
        equipo.setCantidad(15);
        equipo.setEstado("DISPONIBLE");
        equipo.setInstalacionId(1L);
    }

    @Test
    void shouldListarInventario() {
        when(inventarioRepository.findAll()).thenReturn(List.of(equipo));

        List<Inventario> resultado = inventarioService.listar();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Balon de futbol", resultado.get(0).getNombre());
    }

    @Test
    void shouldListarInventarioVacio() {
        when(inventarioRepository.findAll()).thenReturn(List.of());

        List<Inventario> resultado = inventarioService.listar();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void shouldBuscarPorIdCuandoExiste() {
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(equipo));

        Optional<Inventario> resultado = inventarioService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(15, resultado.get().getCantidad());
    }

    @Test
    void shouldBuscarPorIdCuandoNoExiste() {
        when(inventarioRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Inventario> resultado = inventarioService.buscarPorId(99L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void shouldCrearInventario() {
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(equipo);

        Inventario resultado = inventarioService.crear(equipo);

        assertEquals(equipo.getNombre(), resultado.getNombre());
        verify(inventarioRepository, times(1)).save(equipo);
    }

}
