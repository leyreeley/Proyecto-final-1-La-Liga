/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.modelo;

/**
 *
 * @author leire.domsan
 */
public class Jugador {
    
    private int id_jugador;
    private String nombre;
    private String posicion;
    private int dorsal;
    private int id_equipo;
    private String nacionalidad;
    
    public Jugador() {
    }

    public Jugador(int idJugador, String nombre, String posicion, int dorsal, int idEquipo, String nacionalidad) {
        this.id_jugador = idJugador;
        this.nombre = nombre;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.id_equipo = idEquipo;
        this.nacionalidad = nacionalidad;
    }

    public int getIdJugador() {
        return id_jugador;
    }

    public void setIdJugador(int idJugador) {
        this.id_jugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getIdEquipo() {
        return id_equipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.id_equipo = idEquipo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Jugador: " + id_jugador + ", Nombre: " + nombre + ", Posicion: " + posicion + ", Nacionalidad: " + nacionalidad;
    }

    
    
    
}
