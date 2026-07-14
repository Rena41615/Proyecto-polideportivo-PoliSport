package com.polisport.nutricion.service;

import com.polisport.nutricion.model.CategoriaComida;
import com.polisport.nutricion.model.DiaSemana;
import com.polisport.nutricion.model.ObjetivoNutricional;
import com.polisport.nutricion.model.PautaAlimentaria;
import com.polisport.nutricion.model.PlanNutricional;
import com.polisport.nutricion.model.RestriccionAlimentaria;
import com.polisport.nutricion.model.Suplementacion;
import com.polisport.nutricion.model.TipoRestriccion;
import com.polisport.nutricion.model.TipoSuplemento;
import com.polisport.nutricion.repository.PlanNutricionalRepository;
import java.time.LocalDate;
import java.util.ArrayList;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link PlanNutricionalService}, utilizando Mockito
 * para simular el {@link PlanNutricionalRepository} y aislar la logica del servicio.
 */
@ExtendWith(MockitoExtension.class)
class PlanNutricionalServiceTest {

    @Mock
    private PlanNutricionalRepository planNutricionalRepository;

    @InjectMocks
    private PlanNutricionalService planNutricionalService;

    private PlanNutricional planNutricional;

    @BeforeEach
    void setUp() {
        planNutricional = new PlanNutricional();
        planNutricional.setId(1L);
        planNutricional.setAtletaId(42L);
        planNutricional.setDeporte("Futbol");
        planNutricional.setObjetivo(ObjetivoNutricional.GANANCIA_MUSCULAR);
        planNutricional.setFechaInicio(LocalDate.now());
        planNutricional.setCaloriasDiariasGr(2500);
        planNutricional.setProteinaGr(150);
        planNutricional.setCarbohidratosGr(300);
        planNutricional.setLipidosGr(80);
    }

    @Test
    void obtenerTodos_deberiaRetornarListaCompleta() {
        when(planNutricionalRepository.findAll()).thenReturn(List.of(planNutricional));

        List<PlanNutricional> resultado = planNutricionalService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals(planNutricional, resultado.get(0));
        verify(planNutricionalRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarElPlan() {
        when(planNutricionalRepository.findById(1L)).thenReturn(Optional.of(planNutricional));

        Optional<PlanNutricional> resultado = planNutricionalService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(planNutricional, resultado.get());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaRetornarVacio() {
        when(planNutricionalRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<PlanNutricional> resultado = planNutricionalService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void guardarPlanNutricional_conColeccionesPopuladas_deberiaEnlazarElPlanEnCadaHijo() {
        RestriccionAlimentaria restriccion = new RestriccionAlimentaria();
        restriccion.setId(1L);
        restriccion.setTipo(TipoRestriccion.ALERGIA);

        PautaAlimentaria pauta = new PautaAlimentaria();
        pauta.setId(1L);
        pauta.setCategoria(CategoriaComida.DESAYUNO);
        pauta.setDiaSemana(DiaSemana.LUNES);

        Suplementacion suplemento = new Suplementacion();
        suplemento.setId(1L);
        suplemento.setTipo(TipoSuplemento.PROTEINA);
        suplemento.setNombre("Whey Protein");

        planNutricional.setRestricciones(new ArrayList<>(List.of(restriccion)));
        planNutricional.setPautas(new ArrayList<>(List.of(pauta)));
        planNutricional.setSuplementos(new ArrayList<>(List.of(suplemento)));

        when(planNutricionalRepository.save(any(PlanNutricional.class))).thenReturn(planNutricional);

        PlanNutricional resultado = planNutricionalService.guardarPlanNutricional(planNutricional);

        assertSame(planNutricional, resultado);
        // Se verifica que el servicio haya enlazado cada hijo con el plan padre antes de guardar
        assertSame(planNutricional, restriccion.getPlan());
        assertSame(planNutricional, pauta.getPlan());
        assertSame(planNutricional, suplemento.getPlan());
        verify(planNutricionalRepository, times(1)).save(planNutricional);
    }

    @Test
    void guardarPlanNutricional_conColeccionesNulas_noDeberiaFallar() {
        planNutricional.setRestricciones(null);
        planNutricional.setPautas(null);
        planNutricional.setSuplementos(null);

        when(planNutricionalRepository.save(planNutricional)).thenReturn(planNutricional);

        PlanNutricional resultado = planNutricionalService.guardarPlanNutricional(planNutricional);

        assertSame(planNutricional, resultado);
        verify(planNutricionalRepository, times(1)).save(planNutricional);
    }

    @Test
    void buscarPorAtletaId_deberiaRetornarPlanesDelAtleta() {
        when(planNutricionalRepository.findByAtletaId(42L)).thenReturn(List.of(planNutricional));

        List<PlanNutricional> resultado = planNutricionalService.buscarPorAtletaId(42L);

        assertEquals(1, resultado.size());
        assertEquals(planNutricional, resultado.get(0));
        verify(planNutricionalRepository, times(1)).findByAtletaId(42L);
    }
}
