package com.polisport.contratos.service;

import com.polisport.contratos.model.Contrato;
import com.polisport.contratos.repository.ContratosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de dominio encargado de la gestion de los contratos laborales
 * del personal (staff) del polideportivo.
 *
 * <p>Concentra la logica de acceso a datos sobre la entidad {@link Contrato},
 * delegando la persistencia en {@link ContratosRepository}. No realiza
 * validaciones de negocio adicionales: la validacion de los datos de entrada
 * se resuelve en la capa de DTO/controlador mediante Bean Validation.</p>
 *
 * <p>Este servicio no lanza excepciones propias del paquete
 * {@code com.polisport.contratos.exception}; los casos de "no encontrado"
 * se exponen mediante {@link Optional} vacio o listas vacias, dejando a
 * quien invoque (tipicamente el controlador) decidir la respuesta adecuada.</p>
 */
@Service
@Slf4j
public class ContratosService {

    @Autowired
    private ContratosRepository contratosRepository;

    /**
     * Obtiene el listado completo de contratos registrados en el sistema,
     * sin aplicar ningun filtro.
     *
     * @return lista con todos los contratos existentes; puede ser vacia si
     *         no hay contratos registrados, nunca {@code null}.
     */
    public List<Contrato> obtenerTodos() {
        log.info("Consultando todos los contratos registrados");
        return contratosRepository.findAll();
    }

    /**
     * Busca un contrato especifico a partir de su identificador unico.
     *
     * @param id identificador del contrato a buscar.
     * @return un {@link Optional} con el contrato encontrado, o vacio si no
     *         existe ningun contrato con ese ID.
     */
    public Optional<Contrato> obtenerPorId(Long id) {
        log.info("Buscando contrato con ID: {}", id);
        return contratosRepository.findById(id);
    }

    /**
     * Obtiene el historial completo de contratos asociados a un empleado,
     * identificado por su RUN (sin digito verificador).
     *
     * @param run RUN del empleado cuyos contratos se desean consultar.
     * @return lista de contratos del empleado; vacia si el empleado no
     *         tiene contratos registrados.
     */
    public List<Contrato> listarPorEmpleado(Integer run) {
        log.info("Buscando historial de contratos para el empleado RUN: {}", run);
        return contratosRepository.findByRunEmpleado(run);
    }

    /**
     * Crea un nuevo contrato o actualiza uno existente.
     *
     * <p>Al usar {@code save} de Spring Data JPA, si el contrato ya trae un
     * ID existente se actualiza el registro; si no trae ID (o es nulo), se
     * crea uno nuevo.</p>
     *
     * @param contrato entidad con los datos del contrato a guardar.
     * @return el contrato persistido, incluyendo el ID generado en caso de
     *         ser una creacion.
     */
    public Contrato guardar(Contrato contrato) {
        log.info("Guardando contrato para el empleado RUN: {}", contrato.getRunEmpleado());
        return contratosRepository.save(contrato);
    }

    /**
     * Elimina el contrato correspondiente al ID indicado.
     *
     * @param id identificador del contrato a eliminar.
     * @throws org.springframework.dao.EmptyResultDataAccessException si no
     *         existe ningun contrato con el ID indicado (comportamiento
     *         propio de {@code JpaRepository#deleteById}).
     */
    public void eliminar(Long id) {
        log.warn("Eliminando contrato con ID: {}", id);
        contratosRepository.deleteById(id);
    }
}