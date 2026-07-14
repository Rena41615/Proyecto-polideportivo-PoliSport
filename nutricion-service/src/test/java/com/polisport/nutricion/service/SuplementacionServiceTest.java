package com.polisport.nutricion.service;

import com.polisport.nutricion.model.Suplementacion;
import com.polisport.nutricion.model.TipoSuplemento;
import com.polisport.nutricion.model.UnidadMedida;
import com.polisport.nutricion.repository.SuplementacionRepository;
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
 * Pruebas unitarias de {@link SuplementacionService}, utilizando Mockito
 * para simular el {@link SuplementacionRepository} y aislar la logica del servicio.
 */
@ExtendWith(MockitoExtension.class)
class SuplementacionServiceTest {

    @Mock
    private SuplementacionRepository suplementacionRepository;

    @InjectMocks
    private SuplementacionService suplementacionService;

    private Suplementacion suplementacion;

    @BeforeEach
    void setUp() {
        suplementacion = new Suplementacion();
        suplementacion.setId(1L);
        suplementacion.setTipo(TipoSuplemento.PROTEINA);
        suplementacion.setNombre("Whey Protein Isolate");
        suplementacion.setDosis(25);
        suplementacion.setUnidad(UnidadMedida.GRAMO);
    }

    @Test
    void obtenerTodos_deberiaRetornarListaCompleta() {
        when(suplementacionRepository.findAll()).thenReturn(List.of(suplementacion));

        List<Suplementacion> resultado = suplementacionService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals(suplementacion, resultado.get(0));
        verify(suplementacionRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarLaSuplementacion() {
        when(suplementacionRepository.findById(1L)).thenReturn(Optional.of(suplementacion));

        Optional<Suplementacion> resultado = suplementacionService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(suplementacion, resultado.get());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaRetornarVacio() {
        when(suplementacionRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Suplementacion> resultado = suplementacionService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void guardarSuplementacion_deberiaPersistirYRetornarLaEntidad() {
        when(suplementacionRepository.save(suplementacion)).thenReturn(suplementacion);

        Suplementacion resultado = suplementacionService.guardarSuplementacion(suplementacion);

        assertEquals(suplementacion, resultado);
        verify(suplementacionRepository, times(1)).save(suplementacion);
    }
}
