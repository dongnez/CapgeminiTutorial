package com.ccsw.tutorial.prestamos;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.prestamos.model.Prestamo;
import com.ccsw.tutorial.prestamos.model.PrestamoDto;
import com.ccsw.tutorial.prestamos.model.PrestamoSearchDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Prestamo", description = "API of Category")
@RequestMapping(value = "/prestamo")
@RestController
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto de búsqueda
     * @return {@link Page} de {@link PrestamoDto}
     */
    @Operation(summary = "Find Page", description = "Method that return a page of Prestamo")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<PrestamoDto> findPageFilter(@RequestBody PrestamoSearchDto dto) {

        Page<Prestamo> page = this.prestamoService.findPageFilter(dto);

        return new PageImpl<>(
                page.getContent().stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList()),
                page.getPageable(), page.getTotalElements());
    }

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Prestamo")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.prestamoService.delete(id);
    }

    /**
     * Recupera un listado de autores {@link Prestamo}
     *
     * @return {@link List} de {@link PrestamoDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Prestamos")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<PrestamoDto> findAll() {

        List<Prestamo> authors = this.prestamoService.findAll();

        return authors.stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     * @throws Exception
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Prestamo")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public ResponseEntity<String> save(@PathVariable(name = "id", required = false) Long id,
            @RequestBody PrestamoDto dto) throws Exception {

        try {
            this.prestamoService.save(id, dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Manejar la excepción y devolver una respuesta HTTP con un mensaje de error
            String errorMessage = e.getMessage();
            HttpStatus status = HttpStatus.BAD_REQUEST; // Código de estado 400 Bad Request
            return ResponseEntity.status(status).body(errorMessage);
        }

    }

}
