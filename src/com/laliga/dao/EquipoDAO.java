/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.dao;

import com.laliga.modelo.Equipo;
import com.laliga.util.ConexionBD;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author leire.domsan
 */
public class EquipoDAO {

    public List<Equipo> obtenerTodos() {
        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipos"; // Esta será la consulta SQL

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                e.setAnio_fundacion(rs.getString("anio_fundacion"));
                e.setIdEstadio(rs.getInt("id_estadio"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // MÉTODO PARA INSERTAR (Opción 2 del menú consola / FormularioEquipo)
    public boolean insertar(Equipo e) {
        // IMPORTANTE: El script usa "Equipos" (con E mayúscula y en plural)
        String sql = "INSERT INTO Equipos (nombre, anio_fundacion, id_estadio) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());

            // CAMBIO CLAVE: El script dice VARCHAR(4), así que usamos setString
            // Si en tu clase Equipo el año es String, usa e.getAnioFundacion()
            // Si sigue siendo int, usa String.valueOf(e.getAnioFundacion())
            ps.setString(2, e.getAnio_fundacion());

            ps.setInt(3, e.getIdEstadio());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al insertar equipo: " + ex.getMessage());
            return false;
        }
    }
}
