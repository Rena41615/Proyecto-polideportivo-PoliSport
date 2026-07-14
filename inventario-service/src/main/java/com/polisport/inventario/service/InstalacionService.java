package com.polisport.inventario.service;

import com.polisport.inventario.model.Instalacion;
import com.polisport.inventario.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestion de instalaciones deportivas del
 * complejo (canchas, gimnasios, piscinas, etc.).
 * <p>
 * Actua como capa intermedia entre los controladores y el
 * {@link InstalacionRepository}, delegando las operaciones CRUD basicas
 * en Spring Data JPA.
 * </p>
 */
@Service
public class InstalacionService {

    @Autowired
    private InstalacionRepository instalacionRepository;

    /**
     * Obtiene todas las instalaciones registradas.
     *
     * @return lista con todas las instalaciones; vacia si no hay registros
     */
    public List<Instalacion> listar() {
        return instalacionRepository.findAll();
    }

    /**
     * Busca una instalacion por su identificador.
     *
     * @param id identificador de la instalacion
     * @return {@link Optional} con la instalacion encontrada, o vacio si no existe
     */
    public Optional<Instalacion> buscarPorId(Long id) {
        return instalacionRepository.findById(id);
    }

    /**
     * Crea (persiste) una nueva instalacion.
     *
     * @param instalacion datos de la instalacion a crear
     * @return la instalacion persistida, incluyendo el id generado
     */
    public Instalacion crear(Instalacion instalacion) {
        return instalacionRepository.save(instalacion);
    }

    /**
     * Actualiza una instalacion existente. Al utilizar {@code save}, si la
     * entidad ya posee un id valido se actualiza el registro; en caso
     * contrario se crea uno nuevo.
     *
     * @param instalacion instalacion con los datos actualizados
     * @return la instalacion persistida con los cambios aplicados
     */
    public Instalacion actualizar(Instalacion instalacion) {
        return instalacionRepository.save(instalacion);
    }

    /**
     * Elimina una instalacion por su identificador.
     *
     * @param id identificador de la instalacion a eliminar
     * @throws org.springframework.dao.EmptyResultDataAccessException si no existe
     *         una instalacion con el id indicado
     */
    public void eliminar(Long id) {
        instalacionRepository.deleteById(id);
    }
}