package com.ccsw.tutorial.client;

import java.util.List;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

public interface ClientService {
    /**
     * Recupera una {@link Category} a partir de su ID
     *
     * @param id PK de la entidad
     * @return {@link Category}
     */
    Client get(Long id);

    /**
     * Método para recuperar todas las {@link Category}
     *
     * @return {@link List} de {@link Category}
     */
    List<Client> findAll();

    /**
     * Método para crear o actualizar una {@link Category}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, ClientDto dto);

    /**
     * Método para borrar una {@link Category}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /*
     * Recupera un cliente
     * 
     * @return {@link Client} de {@link Client}
     */
    Client findById(Long id) throws Exception;
}
