/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.modelo;

/**
 *
 * @author leire.domsan
 */
public class Estadio {
    
    private int id_estadio;
    private String nombre;
    private int capacidad;
    private int id_ciudad; // FK a la ciudad

    public Estadio() {
    }

    public Estadio(int idEstadio, String nombre, int capacidad, int idCiudad) {
        this.id_estadio = idEstadio;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.id_ciudad = idCiudad;
    }

    public int getIdEstadio() {
        return id_estadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.id_estadio = idEstadio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getIdCiudad() {
        return id_ciudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.id_ciudad = idCiudad;
    }

    @Override
    public String toString() {
        return nombre + " (Capacidad: " + capacidad + ")";
    }
    
    
}
