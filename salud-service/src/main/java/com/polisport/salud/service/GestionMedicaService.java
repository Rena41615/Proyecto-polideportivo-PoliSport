package com.polisport.salud.service;

import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.repository.GestionMedicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * GestionMedicaService: lógica de negocio para la administración de los
 * registros médicos ({@link GestionMedica}) de los atletas.
 * <p>
 * Actúa como capa intermedia entre el controlador REST
 * ({@code GestionMedicaController}) y la persistencia
 * ({@link GestionMedicaRepository}), ofreciendo las operaciones CRUD básicas
 * (listar, buscar, crear, actualizar, eliminar) así como la búsqueda de
 * registros médicos asociados a un atleta específico.
 */
@Slf4j
@Service
public class GestionMedicaService {

    @Autowired
    private GestionMedicaRepository gestionMedicaRepository;

    /**
     * Obtiene la lista completa de registros médicos almacenados.
     *
     * @return lista con todos los registros médicos; vacía si no existe ninguno
     */
    public List<GestionMedica> listar() {
        log.info("Obteniendo la lista de todos los registros medicos");
        return gestionMedicaRepository.findAll();
    }

    /**
     * Busca un registro médico por su identificador único.
     *
     * @param id identificador del registro médico
     * @return un {@link Optional} con el registro encontrado, o vacío si no existe
     */
    public Optional<GestionMedica> buscarPorId(Long id) {
        log.info("Buscando registro medico con ID: {}", id);
        return gestionMedicaRepository.findById(id);
    }

    /**
     * Crea y persiste un nuevo registro médico.
     *
     * @param gestion datos del registro médico a crear
     * @return el registro médico ya persistido, incluyendo el ID generado
     */
    public GestionMedica crear(GestionMedica gestion) {
        log.info("Creando nuevo registro medico");
        return gestionMedicaRepository.save(gestion);
    }

    /**
     * Elimina un registro médico según su identificador.
     *
     * @param id identificador del registro médico a eliminar
     * @throws org.springframework.dao.EmptyResultDataAccessException si no existe
     *         ningún registro médico con el ID indicado
     */
    public void eliminar(Long id) {
        log.warn("Eliminando registro medico con ID: {}", id);
        gestionMedicaRepository.deleteById(id);
    }

    /**
     * Actualiza un registro médico existente.
     * <p>
     * Al usar {@code save}, si el ID del registro ya existe se sobrescriben
     * sus datos; si no existe, se crea uno nuevo con ese ID (comportamiento
     * estándar de Spring Data JPA).
     *
     * @param gestion registro médico con los datos actualizados (debe incluir el ID)
     * @return el registro médico actualizado y persistido
     */
    public GestionMedica actualizar(GestionMedica gestion) {
        log.info("Actualizando registro medico con ID: {}", gestion.getId());
        return gestionMedicaRepository.save(gestion);
    }

    /**
     * Busca todos los registros médicos asociados a un atleta específico.
     *
     * @param atletaId identificador del atleta
     * @return lista de registros médicos del atleta; vacía si no tiene ninguno
     */
    public List<GestionMedica> buscarPorAtletaId(Long atletaId) {
        log.info("Buscando registros medicos para el atleta ID: {}", atletaId);
        return gestionMedicaRepository.findByAtletaId(atletaId);
    }
}