package com.polisport.entrenamiento.service;

import com.polisport.entrenamiento.model.Entrenamiento;
import com.polisport.entrenamiento.repository.EntrenamientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de dominio encargado de la planificacion y el seguimiento de las sesiones
 * de entrenamiento de los atletas.
 * <p>
 * Centraliza la logica de acceso a datos sobre {@link Entrenamiento}, delegando la
 * persistencia en {@link EntrenamientoRepository}. Permite consultar el historial de
 * sesiones (globales, por identificador o por atleta participante), asi como registrar,
 * actualizar y eliminar sesiones de entrenamiento.
 */
@Service
@Slf4j

public class EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    /**
     * Obtiene el listado completo de sesiones de entrenamiento registradas en el sistema.
     *
     * @return lista con todos los entrenamientos existentes; puede ser vacia si no hay registros.
     */
    @Transactional(readOnly = true)
    public List<Entrenamiento> obtenerTodos(){
        log.info("Consultando todos los entrenamientos registros");
        return entrenamientoRepository.findAll();
    }

    /**
     * Busca una sesion de entrenamiento especifica a partir de su identificador.
     *
     * @param id identificador unico de la sesion de entrenamiento.
     * @return un {@link Optional} con el entrenamiento encontrado, o vacio si no existe
     *         ningun registro con ese ID.
     */
    @Transactional(readOnly = true)
    public Optional<Entrenamiento> obtenerPorId(Long id){
        log.info("Buscando entrenamiento con ID: {}", id);
        return entrenamientoRepository.findById(id);
    }

    /**
     * Registra una nueva sesion de entrenamiento o actualiza una existente, segun si la
     * entidad recibida trae o no un identificador asignado.
     *
     * @param entrenamiento entidad con los datos de la sesion a persistir.
     * @return el entrenamiento persistido, incluyendo el ID generado o actualizado.
     */
    public Entrenamiento guardar(Entrenamiento entrenamiento){
        log.info("Registrando sesion de entrenamiento para el entrenador RUN: {}", entrenamiento.getRunEntrenador());
        return entrenamientoRepository.save(entrenamiento);
    }

    /**
     * Elimina la sesion de entrenamiento identificada por el ID indicado.
     * <p>
     * La eliminacion se delega directamente en el repositorio: si no existe una sesion
     * con ese ID, Spring Data lanzara una excepcion en tiempo de ejecucion.
     *
     * @param id identificador unico de la sesion de entrenamiento a eliminar.
     */
    public void eliminar(Long id){
        log.warn("Eliminando entrenamiento con ID: {}", id);
        entrenamientoRepository.deleteById(id);
    }

    /**
     * Obtiene el historial de sesiones de entrenamiento en las que ha participado un atleta.
     *
     * @param run RUN del atleta (sin digito verificador) por el cual filtrar las sesiones.
     * @return lista de entrenamientos donde el atleta figura como participante; puede ser
     *         vacia si el atleta no tiene sesiones registradas.
     */
    @Transactional(readOnly = true)
    public List<Entrenamiento> listarPorAtleta(Integer run){
        log.info("Obteniendo historial de entrenamientos para el atleta RUN: {}", run);
        // La busqueda se resuelve con una consulta JPQL que recorre la coleccion de atletas participantes
        return entrenamientoRepository.buscarPorAtleta(run);
    }

}