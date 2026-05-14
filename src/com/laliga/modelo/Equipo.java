/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.modelo;

/**
 *
 * @author leire.domsan
 */
public class Equipo {
    
    private int id_equipo;
    private String nombre;
    private int anio_fundacion;
    private int id_estadio;
    
    public Equipo() {
    }

    public Equipo(int idEquipo, String nombre, int anioFundacion, int idEstadio) {
        this.id_equipo = idEquipo;
        this.nombre = nombre;
        this.anio_fundacion = anioFundacion;
        this.id_estadio = idEstadio;
    }

    public int getIdEquipo() {
        return id_equipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.id_equipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioFundacion() {
        return anio_fundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anio_fundacion = anioFundacion;
    }

    public int getIdEstadio() {
        return id_estadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.id_estadio = idEstadio;
    }

    @Override
    public String toString() {
        return nombre + " (Fundado en " + anio_fundacion + ")";
    }
    
    
}
