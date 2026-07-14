package com.polisport.competencia.service;

import com.polisport.competencia.model.Competencia;
import com.polisport.competencia.repository.CompetenciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de dominio para la gestion de competencias deportivas.
 * <p>
 * Centraliza las operaciones CRUD sobre la entidad {@link Competencia} y la
 * logica de consulta relacionada con la participacion de atletas en dichas
 * competencias (por ejemplo, obtener las competencias en las que un atleta
 * se encuentra inscrito segun su RUN). Actua como intermediario entre el
 * {@code com.polisport.competencia.controller.CompetenciaController} (a
 * traves de sus DTOs) y el {@link CompetenciaRepository}, delegando en
 * este ultimo el acceso a la base de datos.
 */
@Service
@Slf4j

public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    /**
     * Obtiene el listado completo de competencias registradas en el sistema.
     *
     * @return una lista con todas las competencias existentes; puede estar
     *         vacia si no hay competencias registradas.
     */
    @Transactional(readOnly = true)
    public List<Competencia> obtenerTodas(){
        log.info("Consultando listado de todas las competencias");
        return competenciaRepository.findAll();
    }

    /**
     * Busca una competencia especifica a partir de su identificador.
     *
     * @param id identificador unico de la competencia a buscar.
     * @return un {@link Optional} con la competencia encontrada, o vacio si
     *         no existe ninguna competencia con el ID indicado.
     */
    @Transactional(readOnly = true)
    public Optional<Competencia> obtenerPorId(Long id){
        log.info("Buscando competencia con ID: {}", id);
        return competenciaRepository.findById(id);
    }

    /**
     * Registra una nueva competencia o actualiza una existente.
     * <p>
     * Si la competencia recibida ya posee un ID valido, JPA la trata como
     * una actualizacion; en caso contrario se crea un nuevo registro.
     *
     * @param competencia entidad con los datos de la competencia a persistir.
     * @return la competencia persistida, con el ID asignado por la base de datos.
     */
    public Competencia guardar(Competencia competencia){
        log.info("Registrando competencia: {}", competencia.getNombreCompetencia());
        return competenciaRepository.save(competencia);
    }

    /**
     * Elimina una competencia del sistema segun su identificador.
     *
     * @param id identificador unico de la competencia a eliminar.
     * @throws org.springframework.dao.EmptyResultDataAccessException si no
     *         existe ninguna competencia con el ID indicado (lanzada
     *         internamente por el repositorio al intentar eliminarla).
     */
    public void eliminar(Long id){
        log.warn("Eliminando competencia con ID: {}", id);
        competenciaRepository.deleteById(id);
    }

    /**
     * Lista las competencias en las que un atleta se encuentra inscrito.
     *
     * @param run RUN del atleta cuyas competencias se desean consultar.
     * @return lista de competencias asociadas al atleta; vacia si el atleta
     *         no esta inscrito en ninguna competencia.
     */
    @Transactional(readOnly = true)
    public List<Competencia> listarPorAtleta(Integer run) {
        log.info("Buscando competencias para el atleta con run: {}", run);
        // Consulta las competencias cuya lista de inscritos contiene el run recibido
        return competenciaRepository.buscarPorAtletaInscrito(run);
    }

}