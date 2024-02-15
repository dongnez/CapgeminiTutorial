package com.ccsw.tutorial.prestamos;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.prestamos.model.Prestamo;
import com.ccsw.tutorial.prestamos.model.PrestamoDto;
import com.ccsw.tutorial.prestamos.model.PrestamoSearchDto;

public interface PrestamoService {

    /**
     * Guarda o modifica un juego, dependiendo de si el identificador está o no
     * informado
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, PrestamoDto dto) throws Exception;

    /**
     * Obtiene prestamo por id
     */
    Prestamo get(Long id);

    /**
     * Encuentra autores por pagina
     */
    Page<Prestamo> findPage(PrestamoSearchDto dto);

    /**
     * Encuentra autores por pagina
     */
    Page<Prestamo> findPageFilter(PrestamoSearchDto dto);

    /**
     * Recupera un listado de autores {@link Prestamo}
     *
     * @return {@link List} de {@link Prestamo}
     */
    List<Prestamo> findAll();

    /**
     * Elimina un prestamo
     * 
     * @param id
     */
    void delete(Long id) throws Exception;
}
