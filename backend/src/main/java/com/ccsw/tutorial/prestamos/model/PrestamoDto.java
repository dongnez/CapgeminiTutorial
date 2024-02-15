package com.ccsw.tutorial.prestamos.model;

import java.sql.Date;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;

public class PrestamoDto {

    @Override
    public String toString() {
        return "PrestamoDto [id=" + id + ", game=" + game + ", client=" + client + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    private Long id;

    private GameDto game;

    private ClientDto client;

    private Date fechaInicio;

    private Date fechaFin;
}
