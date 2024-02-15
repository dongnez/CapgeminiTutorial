package com.ccsw.tutorial.prestamos;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamos.model.Prestamo;
import com.ccsw.tutorial.prestamos.model.PrestamoDto;
import com.ccsw.tutorial.prestamos.model.PrestamoFilterDto;
import com.ccsw.tutorial.prestamos.model.PrestamoSearchDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    GameService gameService;

    @Override
    public void save(Long id, PrestamoDto dto) throws Exception {

        Prestamo prestamo;

        if (id == null) {
            prestamo = new Prestamo();
        } else {
            prestamo = this.prestamoRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, prestamo, "id", "client", "game");

        prestamo.setClient(clientService.get(dto.getClient().getId()));
        prestamo.setGame(gameService.get(dto.getGame().getId()));

        // Comprobamos fecha son correlativas
        if (prestamo.getFechaFin().before(prestamo.getFechaInicio())) {
            throw new Exception("Fecha final no puede ser anterior a la fecha de inicio.");
        }

        // Comprobar periodo máximo de dias es <= 14
        long diferenciaEnMilisegundos = prestamo.getFechaFin().getTime() - prestamo.getFechaInicio().getTime();
        long dias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);
        if (dias > 14) {
            throw new Exception("El período máximo de préstamo no puede exceder los 14 días.");
        }

        // Comprobar juego ya prestado
        boolean gameOut = this.prestamoRepository.existsActivePrestamosForGame(prestamo.getGame(),
                prestamo.getFechaInicio(), prestamo.getFechaFin());
        if (gameOut) {
            throw new Exception("El juego ya está prestado a otro cliente en el mismo día.");
        }

        // No prestar 2 juegos en un dia
        int numero_prestado = this.prestamoRepository.countActivePrestamosForClient(prestamo.getClient(),
                prestamo.getFechaInicio(), prestamo.getFechaFin());

        if (numero_prestado == 2) {
            throw new Exception("La persona no puede prestar más juegos en este rango de fechas");
        }

        this.prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo get(Long id) {
        return this.prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Prestamo> findPage(PrestamoSearchDto dto) {
        return this.prestamoRepository.findAll(dto.getPageable().getPageable());
    }

    @Override
    public Page<Prestamo> findPageFilter(PrestamoSearchDto dto) {

        PrestamoFilterDto filterDto = dto.getFilterParams();

        PrestamoSpecification gameSpec = new PrestamoSpecification(
                new SearchCriteria("game.id", ":", filterDto.getIdGame()));
        PrestamoSpecification clientSpec = new PrestamoSpecification(
                new SearchCriteria("client.id", ":", filterDto.getIdClient()));

        PrestamoSpecification dateSpec = new PrestamoSpecification(
                new SearchCriteria("fechaFin", "<=", filterDto.getDate()));
        PrestamoSpecification dateIniSpec = new PrestamoSpecification(
                new SearchCriteria("fechaInicio", ">=", filterDto.getDate()));

        Specification<Prestamo> spec = Specification.where(gameSpec).and(clientSpec).and(dateSpec).and(dateIniSpec);

        return this.prestamoRepository.findAll(spec, dto.getPageable().getPageable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Prestamo> findAll() {

        return (List<Prestamo>) this.prestamoRepository.findAll();
    }

    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.prestamoRepository.deleteById(id);
    }

}
