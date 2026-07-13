package com.polisport.atletas.service;

import com.polisport.atletas.exception.ResourceNotFoundException;
import com.polisport.atletas.model.Atleta;
import com.polisport.atletas.repository.AtletaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio encargado de la gestion de atletas dentro del dominio deportivo de PoliSport.
 * <p>
 * Concentra la logica de negocio para el CRUD de la entidad {@link Atleta}, delegando
 * la persistencia en {@link AtletaRepository}. Actua como intermediario entre el
 * controlador (capa web) y la capa de acceso a datos, aplicando validaciones basicas
 * de existencia y registrando trazas de auditoria mediante {@code @Slf4j}.
 */
@Service
@Slf4j // jerarquia de mensajes
public class AtletaService {

    @Autowired // permite comunicacion con el repository
    private AtletaRepository atletaRepository;

    /**
     * Obtiene el listado completo de atletas registrados en el sistema.
     *
     * @return una lista con todos los atletas persistidos; puede estar vacia si no hay registros.
     */
    public List<Atleta> obtenerTodos() {
        log.info("Consultando la lista completa de atletas");
        return atletaRepository.findAll();
    }

    /**
     * Busca un atleta a partir de su identificador unico.
     *
     * @param id identificador (clave primaria) del atleta a buscar.
     * @return un {@link Optional} con el atleta encontrado, o vacio si no existe un atleta con ese ID.
     */
    public Optional<Atleta> obtenerPorId(Long id){
        log.info("Buscando atleta con ID: {}", id);
        return atletaRepository.findById(id);
    }

    /**
     * Registra un nuevo atleta o actualiza uno existente (segun tenga o no ID asignado).
     *
     * @param atleta entidad {@link Atleta} a guardar con sus datos completos.
     * @return el atleta persistido, incluyendo el ID generado en caso de ser un registro nuevo.
     */
    public Atleta guardarAtleta(Atleta atleta){
        log.info("Registrando o actualizando atleta con RUT: {}", atleta.getRunAtleta());
        return atletaRepository.save(atleta);
    }

    /**
     * Elimina de forma definitiva a un atleta segun su identificador.
     *
     * @param id identificador del atleta a eliminar.
     */
    public void eliminarAtleta(Long id){
        log.warn("Eliminando de la base de datos al atleta con ID: {}", id);
        atletaRepository.deleteById(id);
    }

    /**
     * Busca un atleta a partir de su RUN (Rol Unico Nacional).
     *
     * @param run numero de RUN del atleta a buscar.
     * @return un {@link Optional} con el atleta encontrado, o vacio si no existe un atleta con ese RUN.
     */
    public Optional<Atleta> obtenerPorRun(Integer run) {
        return atletaRepository.findByRunAtleta(run);
    }

    /**
     * Actualiza parcialmente (PATCH) los campos de un atleta existente.
     * <p>
     * Solo se modifican los campos presentes en el mapa {@code updates}; el resto de los
     * atributos del atleta permanecen sin cambios. No se permite actualizar el RUN ni el ID
     * por esta via, para proteger la integridad del registro, y los campos de tipo fecha
     * quedan fuera de este PATCH basico por requerir un casteo especial.
     *
     * @param id identificador del atleta a actualizar.
     * @param updates mapa con los pares campo/valor a modificar (las claves deben coincidir
     *                con los nombres de los atributos soportados de {@link Atleta}).
     * @return el atleta con los cambios aplicados y persistidos.
     * @throws ResourceNotFoundException si no existe un atleta con el ID indicado.
     */
    public Atleta actualizarParcial(Long id, Map<String, Object> updates) {
        log.info("Iniciando actualización parcial (PATCH) para el atleta con ID: {}", id);

        // 1. Buscamos el atleta en la base de datos. Si no existe, lanzamos un error.
        Atleta atleta = atletaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atleta no encontrado con ID: " + id));

        // 2. Iteramos sobre el mapa de actualizaciones que nos envió el Controller
        updates.forEach((campo, valor) -> {
            switch (campo) {
                case "primerNombre" -> atleta.setPrimerNombre((String) valor);
                case "segundoNombre" -> atleta.setSegundoNombre((String) valor);
                case "tercerNombre" -> atleta.setTercerNombre((String) valor);
                case "primerApellido" -> atleta.setPrimerApellido((String) valor);
                case "segundoApellido" -> atleta.setSegundoApellido((String) valor);
                case "email" -> atleta.setEmail((String) valor);
                case "deportePrincipal" -> atleta.setDeportePrincipal((String) valor);
                case "categoria" -> atleta.setCategoria((String) valor);
                case "historialDeportivo" -> atleta.setHistorialDeportivo((String) valor);

                // Nota: Fechas (LocalDate) requieren un tratamiento especial de casteo,
                // por lo que se omiten en este PATCH básico por seguridad.
                // Nota 2: NO incluimos "runAtleta" ni "id" para proteger la integridad.
            }
        });

        // 3. Guardamos los cambios
        log.info("Atleta con ID {} actualizado parcialmente de forma exitosa", id);
        return atletaRepository.save(atleta);
    }
}