/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.controlador;

import javax.swing.JOptionPane;
/**
 *
 * @author leire.domsan
 */
public class LigaControlador {
    
    /**
     * Valida si los datos de un jugador son lógicos antes de mandarlos al DAO.
     */
    public static boolean validarJugador(String nombre, String posicion, int dorsal, String nacionalidad) {
        
        if (nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            return false;
        }
        
        if (dorsal < 1 || dorsal > 99) {
            JOptionPane.showMessageDialog(null, "El dorsal debe estar entre 1 y 99.");
            return false;
        }
        
        if (nacionalidad.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes indicar una nacionalidad.");
            return false;
        }

        return true; // Si pasa todos los filtros, es válido
    }
}
