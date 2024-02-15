package com.ccsw.tutorial.prestamos.model;

public class PrestamoFilterDto {

    @Override
    public String toString() {
        return "PrestamoFilterDto [idGame=" + idGame + ", idClient=" + idClient + ", date=" + date + "]";
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PrestamoFilterDto() {

    }

    public PrestamoFilterDto(Long idGame, Long idClient, String date) {
        super();
        this.idGame = idGame;
        this.idClient = idClient;
        this.date = date;
    }

    private Long idGame;
    private Long idClient;
    private String date;
}
