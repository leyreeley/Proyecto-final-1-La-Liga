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
    private String anio_fundacion;
    private int id_estadio;
    
    public Equipo() {
    }

    public Equipo(int id_equipo, String nombre, String anio_fundacion, int id_estadio) {
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.anio_fundacion = anio_fundacion;
        this.id_estadio = id_estadio;
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

    public String getAnio_fundacion() {
        return anio_fundacion;
    }

    public void setAnio_fundacion(String anio_fundacion) {
        this.anio_fundacion = anio_fundacion;
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
