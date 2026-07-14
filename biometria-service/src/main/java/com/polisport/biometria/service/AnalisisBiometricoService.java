package com.polisport.biometria.service;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.repository.AnalisisBiometricoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de dominio encargado de la gestion de los analisis biometricos de los atletas.
 * <p>
 * Actua como intermediario entre la capa de presentacion (controller) y la capa de
 * persistencia ({@link AnalisisBiometricoRepository}), centralizando las operaciones CRUD
 * sobre la entidad {@link AnalisisBiometrico} (peso, altura, IMC, porcentaje de grasa,
 * masa muscular, VO2 Max, frecuencia cardiaca en reposo e indicador de rendimiento).
 * No aplica logica de negocio adicional: delega directamente en Spring Data JPA y deja
 * que sea el controller quien decida como responder ante la ausencia de un registro.
 */
@Slf4j
@Service
public class AnalisisBiometricoService {

    @Autowired
    private AnalisisBiometricoRepository analisisBiometricoRepository;

    /**
     * Obtiene todos los analisis biometricos registrados en el sistema.
     *
     * @return lista con todos los analisis biometricos existentes; vacia si no hay registros
     */
    public List<AnalisisBiometrico> listar() {
        log.info("Obteniendo la lista de todos los analisis biometricos");
        return analisisBiometricoRepository.findAll();
    }

    /**
     * Busca un analisis biometrico a partir de su identificador.
     *
     * @param id identificador unico del analisis biometrico a buscar
     * @return un {@link Optional} con el analisis biometrico si existe, o vacio si no se encuentra
     */
    public Optional<AnalisisBiometrico> buscarPorId(Long id) {
        log.info("Buscando analisis biometrico con ID: {}", id);
        return analisisBiometricoRepository.findById(id);
    }

    /**
     * Crea y persiste un nuevo analisis biometrico.
     *
     * @param analisis analisis biometrico a crear (sin ID, se genera automaticamente)
     * @return el analisis biometrico ya persistido, incluyendo el ID generado
     */
    public AnalisisBiometrico crear(AnalisisBiometrico analisis) {
        log.info("Creando nuevo analisis biometrico para el atleta ID: {}", analisis.getAtletaId());
        return analisisBiometricoRepository.save(analisis);
    }

    /**
     * Elimina un analisis biometrico existente a partir de su identificador.
     *
     * @param id identificador unico del analisis biometrico a eliminar
     * @throws org.springframework.dao.EmptyResultDataAccessException si no existe un analisis
     *         biometrico con el ID proporcionado
     */
    public void eliminar(Long id) {
        log.warn("Eliminando analisis biometrico con ID: {}", id);
        analisisBiometricoRepository.deleteById(id);
    }

    /**
     * Actualiza un analisis biometrico existente.
     * <p>
     * Al usar {@code save}, si el ID del analisis ya existe se sobrescribe el registro;
     * si no existe, JPA lo trataria como una insercion nueva (upsert implicito de Spring Data).
     *
     * @param analisis analisis biometrico con el ID del registro a actualizar y los nuevos datos
     * @return el analisis biometrico actualizado y persistido
     */
    public AnalisisBiometrico actualizar(AnalisisBiometrico analisis) {
        log.info("Actualizando analisis biometrico con ID: {}", analisis.getId());
        return analisisBiometricoRepository.save(analisis);
    }
}