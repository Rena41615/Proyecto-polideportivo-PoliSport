package com.polisport.nutricion.service;

import com.polisport.nutricion.model.CategoriaComida;
import com.polisport.nutricion.model.DiaSemana;
import com.polisport.nutricion.model.PautaAlimentaria;
import com.polisport.nutricion.repository.PautaAlimentariaRepository;
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
 * Pruebas unitarias de {@link PautaAlimentariaService}, utilizando Mockito
 * para simular el {@link PautaAlimentariaRepository} y aislar la logica del servicio.
 */
@ExtendWith(MockitoExtension.class)
class PautaAlimentariaServiceTest {

    @Mock
    private PautaAlimentariaRepository pautaAlimentariaRepository;

    @InjectMocks
    private PautaAlimentariaService pautaAlimentariaService;

    private PautaAlimentaria pautaAlimentaria;

    @BeforeEach
    void setUp() {
        pautaAlimentaria = new PautaAlimentaria();
        pautaAlimentaria.setId(1L);
        pautaAlimentaria.setCategoria(CategoriaComida.DESAYUNO);
        pautaAlimentaria.setDiaSemana(DiaSemana.LUNES);
        pautaAlimentaria.setDescripcion("Avena con frutas");
    }

    @Test
    void obtenerTodos_deberiaRetornarListaCompleta() {
        when(pautaAlimentariaRepository.findAll()).thenReturn(List.of(pautaAlimentaria));

        List<PautaAlimentaria> resultado = pautaAlimentariaService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals(pautaAlimentaria, resultado.get(0));
        verify(pautaAlimentariaRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarLaPauta() {
        when(pautaAlimentariaRepository.findById(1L)).thenReturn(Optional.of(pautaAlimentaria));

        Optional<PautaAlimentaria> resultado = pautaAlimentariaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(pautaAlimentaria, resultado.get());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaRetornarVacio() {
        when(pautaAlimentariaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<PautaAlimentaria> resultado = pautaAlimentariaService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void guardarPautaAlimentaria_deberiaPersistirYRetornarLaEntidad() {
        when(pautaAlimentariaRepository.save(pautaAlimentaria)).thenReturn(pautaAlimentaria);

        PautaAlimentaria resultado = pautaAlimentariaService.guardarPautaAlimentaria(pautaAlimentaria);

        assertEquals(pautaAlimentaria, resultado);
        verify(pautaAlimentariaRepository, times(1)).save(pautaAlimentaria);
    }
}
