package com.polisport.nutricion.service;

import com.polisport.nutricion.model.RestriccionAlimentaria;
import com.polisport.nutricion.model.TipoRestriccion;
import com.polisport.nutricion.repository.RestriccionAlimentariaRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link RestriccionAlimentariaService}, utilizando Mockito
 * para simular el {@link RestriccionAlimentariaRepository} y aislar la logica del servicio.
 */
@ExtendWith(MockitoExtension.class)
class RestriccionAlimentariaServiceTest {

    @Mock
    private RestriccionAlimentariaRepository restriccionAlimentariaRepository;

    @InjectMocks
    private RestriccionAlimentariaService restriccionAlimentariaService;

    private RestriccionAlimentaria restriccionAlimentaria;

    @BeforeEach
    void setUp() {
        restriccionAlimentaria = new RestriccionAlimentaria();
        restriccionAlimentaria.setId(1L);
        restriccionAlimentaria.setTipo(TipoRestriccion.ALERGIA);
        restriccionAlimentaria.setDescripcion("Alergia a los frutos secos");
    }

    @Test
    void obtenerTodos_deberiaRetornarListaCompleta() {
        when(restriccionAlimentariaRepository.findAll()).thenReturn(List.of(restriccionAlimentaria));

        List<RestriccionAlimentaria> resultado = restriccionAlimentariaService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals(restriccionAlimentaria, resultado.get(0));
        verify(restriccionAlimentariaRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarLaRestriccion() {
        when(restriccionAlimentariaRepository.findById(1L)).thenReturn(Optional.of(restriccionAlimentaria));

        Optional<RestriccionAlimentaria> resultado = restriccionAlimentariaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(restriccionAlimentaria, resultado.get());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaRetornarVacio() {
        when(restriccionAlimentariaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<RestriccionAlimentaria> resultado = restriccionAlimentariaService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void guardarRestriccionAlimentaria_deberiaPersistirYRetornarLaEntidad() {
        when(restriccionAlimentariaRepository.save(restriccionAlimentaria)).thenReturn(restriccionAlimentaria);

        RestriccionAlimentaria resultado = restriccionAlimentariaService.guardarRestriccionAlimentaria(restriccionAlimentaria);

        assertEquals(restriccionAlimentaria, resultado);
        verify(restriccionAlimentariaRepository, times(1)).save(restriccionAlimentaria);
    }
}
