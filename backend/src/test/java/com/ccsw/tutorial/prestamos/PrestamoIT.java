package com.ccsw.tutorial.prestamos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.common.date.ParseDate;
import com.ccsw.tutorial.common.pagination.PageableRequest;
import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.prestamos.model.PrestamoDto;
import com.ccsw.tutorial.prestamos.model.PrestamoFilterDto;
import com.ccsw.tutorial.prestamos.model.PrestamoSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrestamoIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/prestamo";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<ResponsePage<PrestamoDto>> responseTypePage = new ParameterizedTypeReference<ResponsePage<PrestamoDto>>() {
    };
    ParameterizedTypeReference<String> responseTypeString = new ParameterizedTypeReference<String>() {
    };

    @Test
    public void findPagesWithFilterGame() {

        PrestamoSearchDto prestamoSDto = new PrestamoSearchDto();
        prestamoSDto.setPageable(new PageableRequest(0, 5));
        PrestamoFilterDto filterDto = new PrestamoFilterDto();
        filterDto.setIdGame(1L);
        prestamoSDto.setFilterParams(filterDto);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(prestamoSDto), responseTypePage);

        System.out.println("REsponse" + response);
        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    public void saveWithWrongDatesShoudError() throws ParseException {
        PrestamoDto prestamoDto = new PrestamoDto();
        GameDto dto = new GameDto();
        dto.setId(1L);
        ClientDto dto2 = new ClientDto();
        dto2.setId(1L);

        prestamoDto.setGame(dto);
        prestamoDto.setClient(dto2);

        // Error fecha inicio superior a fecha fin
        prestamoDto.setFechaInicio(ParseDate.parseStringToDate("2024-02-12"));
        prestamoDto.setFechaFin(ParseDate.parseStringToDate("2024-02-11"));

        ResponseEntity<String> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                new HttpEntity<>(prestamoDto), responseTypeString);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Fecha final no puede ser anterior a la fecha de inicio."));
    }

    @Test
    public void saveWith14DaysShoudError() throws ParseException {
        PrestamoDto prestamoDto = new PrestamoDto();
        GameDto dto = new GameDto();
        dto.setId(1L);
        ClientDto dto2 = new ClientDto();
        dto2.setId(1L);

        prestamoDto.setGame(dto);
        prestamoDto.setClient(dto2);

        // Error fecha inicio superior a fecha fin
        prestamoDto.setFechaInicio(ParseDate.parseStringToDate("2024-02-01"));
        prestamoDto.setFechaFin(ParseDate.parseStringToDate("2024-02-16"));

        ResponseEntity<String> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                new HttpEntity<>(prestamoDto), responseTypeString);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("El período máximo de préstamo no puede exceder los 14 días."));
    }

    @Test
    public void saveSameDayShoudError() throws ParseException {
        PrestamoDto prestamoDto = new PrestamoDto();
        GameDto dto = new GameDto();
        dto.setId(1L);
        ClientDto dto2 = new ClientDto();
        dto2.setId(1L);

        prestamoDto.setGame(dto);
        prestamoDto.setClient(dto2);

        // Error fecha inicio superior a fecha fin
        prestamoDto.setFechaInicio(ParseDate.parseStringToDate("2024-02-16"));
        prestamoDto.setFechaFin(ParseDate.parseStringToDate("2024-02-18"));

        ResponseEntity<String> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                new HttpEntity<>(prestamoDto), responseTypeString);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("El juego ya está prestado a otro cliente en el mismo día."));
    }

    @Test
    public void saveGame2PerDayShoudError() throws ParseException {
        PrestamoDto prestamoDto = new PrestamoDto();
        GameDto dto = new GameDto();
        dto.setId(2L);
        ClientDto dto2 = new ClientDto();
        dto2.setId(2L);

        prestamoDto.setGame(dto);
        prestamoDto.setClient(dto2);

        // Error fecha inicio superior a fecha fin
        prestamoDto.setFechaInicio(ParseDate.parseStringToDate("2024-02-16"));
        prestamoDto.setFechaFin(ParseDate.parseStringToDate("2024-02-18"));

        ResponseEntity<String> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                new HttpEntity<>(prestamoDto), responseTypeString);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("La persona no puede prestar más juegos en este rango de fechas"));
    }

}
