/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author leire.domsan
 */
public class ConexionBD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/futbol";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Cargamos el driver (asegurate de añadir el JAR al proyecto en 'Libraries')
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }
}
