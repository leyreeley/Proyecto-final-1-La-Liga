/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.modelo;

/**
 *
 * @author leire.domsan
 */
public class Ciudad {
    
    private int id_ciudad;
    private String nombre;

    public Ciudad() {
    }

    public Ciudad(int idCiudad, String nombre) {
        this.id_ciudad = idCiudad;
        this.nombre = nombre;
    }

    public int getIdCiudad() {
        return id_ciudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.id_ciudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
