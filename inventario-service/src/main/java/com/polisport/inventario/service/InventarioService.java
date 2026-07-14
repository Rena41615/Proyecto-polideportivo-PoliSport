package com.polisport.inventario.service;

import com.polisport.inventario.model.Inventario;
import com.polisport.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestion del equipamiento deportivo
 * (balones, material, equipos) asociado a las instalaciones del complejo.
 * <p>
 * Actua como capa intermedia entre los controladores y el
 * {@link InventarioRepository}, delegando las operaciones CRUD basicas
 * en Spring Data JPA.
 * </p>
 */
@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    /**
     * Obtiene todos los articulos de inventario registrados.
     *
     * @return lista con todos los articulos; vacia si no hay registros
     */
    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    /**
     * Busca un articulo de inventario por su identificador.
     *
     * @param id identificador del articulo
     * @return {@link Optional} con el articulo encontrado, o vacio si no existe
     */
    public Optional<Inventario> buscarPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    /**
     * Crea (persiste) un nuevo articulo de inventario.
     *
     * @param inventario datos del articulo a crear
     * @return el articulo persistido, incluyendo el id generado
     */
    public Inventario crear(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    /**
     * Actualiza un articulo de inventario existente. Al utilizar
     * {@code save}, si la entidad ya posee un id valido se actualiza el
     * registro; en caso contrario se crea uno nuevo.
     *
     * @param inventario articulo con los datos actualizados
     * @return el articulo persistido con los cambios aplicados
     */
    public Inventario actualizar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    /**
     * Elimina un articulo de inventario por su identificador.
     *
     * @param id identificador del articulo a eliminar
     * @throws org.springframework.dao.EmptyResultDataAccessException si no existe
     *         un articulo con el id indicado
     */
    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }
}