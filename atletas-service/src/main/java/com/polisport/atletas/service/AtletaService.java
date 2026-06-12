package com.polisport.atletas.service;

import com.polisport.atletas.model.Atleta;
import com.polisport.atletas.repository.AtletaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j // jerarquia de mensajes
public class AtletaService {

    @Autowired // permite comunicacion con el repository
    private AtletaRepository atletaRepository;

    public List<Atleta> obtenerTodos() {
        log.info("Consultando la lista completa de atletas");
        return atletaRepository.findAll();
    }

    public Optional<Atleta> obtenerPorId(Long id){
        log.info("Buscando atleta con ID: {}", id);
        return atletaRepository.findById(id);
    }

    public Atleta guardarAtleta(Atleta atleta){
        log.info("Registrando o actualizando atleta con RUT: {}", atleta.getRunAtleta());
        return atletaRepository.save(atleta);
    }

    public void eliminarAtleta(Long id){
        log.warn("Eliminando de la base de datos al atleta con ID: {}", id);
        atletaRepository.deleteById(id);
    }

    public Optional<Atleta> obtenerPorRun(Integer run) {
        return atletaRepository.findByRunAtleta(run);
    }

    public Atleta actualizarParcial(Long id, Map<String, Object> updates) {
        log.info("Iniciando actualización parcial (PATCH) para el atleta con ID: {}", id);

        // 1. Buscamos el atleta en la base de datos. Si no existe, lanzamos un error.
        Atleta atleta = atletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta no encontrado con ID: " + id));

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