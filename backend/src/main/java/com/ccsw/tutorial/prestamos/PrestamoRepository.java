package com.ccsw.tutorial.prestamos;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.prestamos.model.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {

    Page<Prestamo> findAll(Pageable pageable);

    @Override
    Page<Prestamo> findAll(Specification<Prestamo> spec, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " + "FROM Prestamo p " + "WHERE p.game = :game "
            + "AND ((p.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR (p.fechaFin BETWEEN :fechaInicio AND :fechaFin))")
    boolean existsActivePrestamosForGame(@Param("game") Game game, @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);

    @Query("SELECT COUNT(p) " + "FROM Prestamo p " + "WHERE p.client = :client "
            + "AND ((p.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR (p.fechaFin BETWEEN :fechaInicio AND :fechaFin))")
    int countActivePrestamosForClient(@Param("client") Client client, @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);

}
