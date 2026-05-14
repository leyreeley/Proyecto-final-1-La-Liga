/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.modelo;

/**
 *
 * @author leire.domsan
 */
public class Estadistica {
    
    private int id_estadistica;
    private int id_jugador; // FK al jugador
    private int goles;
    private int asistencias;
    private int tarjetas_amarillas;
    private int tarjetas_rojas;
    private int partidos_titular;
    private int partidos_suplente;
    private int partidos_sin_jugar;

    public Estadistica() {
    }

    public Estadistica(int idEstadistica, int idJugador, int goles, int asistencias, int tarjetasAmarillas, int tarjetasRojas, int partidosTitular, int partidosSuplente, int partidosSinJugar) {
        this.id_estadistica = idEstadistica;
        this.id_jugador = idJugador;
        this.goles = goles;
        this.asistencias = asistencias;
        this.tarjetas_amarillas = tarjetasAmarillas;
        this.tarjetas_rojas = tarjetasRojas;
        this.partidos_titular = partidosTitular;
        this.partidos_suplente = partidosSuplente;
        this.partidos_sin_jugar = partidosSinJugar;
    }

    public int getIdEstadistica() {
        return id_estadistica;
    }

    public void setIdEstadistica(int idEstadistica) {
        this.id_estadistica = idEstadistica;
    }

    public int getIdJugador() {
        return id_jugador;
    }

    public void setIdJugador(int idJugador) {
        this.id_jugador = idJugador;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getTarjetasAmarillas() {
        return tarjetas_amarillas;
    }

    public void setTarjetasAmarillas(int tarjetasAmarillas) {
        this.tarjetas_amarillas = tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetas_rojas;
    }

    public void setTarjetasRojas(int tarjetasRojas) {
        this.tarjetas_rojas = tarjetasRojas;
    }

    public int getPartidosTitular() {
        return partidos_titular;
    }

    public void setPartidosTitular(int partidosTitular) {
        this.partidos_titular = partidosTitular;
    }

    public int getPartidosSuplente() {
        return partidos_suplente;
    }

    public void setPartidosSuplente(int partidosSuplente) {
        this.partidos_suplente = partidosSuplente;
    }

    public int getPartidosSinJugar() {
        return partidos_sin_jugar;
    }

    public void setPartidosSinJugar(int partidosSinJugar) {
        this.partidos_sin_jugar = partidosSinJugar;
    }

    @Override
    public String toString() {
        return "Goles: " + goles + "\nAsistencias: " + asistencias + "\nTarjetas amarillas: " + tarjetas_amarillas + "\nTarjetas rojas: " + tarjetas_rojas + "\nPartidos titular: " + partidos_titular + "\nPartidos suplente: " + partidos_suplente + "\nPartidos sin jugar: " + partidos_sin_jugar;
    }

    
    
    
}
