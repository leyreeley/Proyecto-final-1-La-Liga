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

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                e.setAnioFundacion(rs.getInt("anio_fundacion"));
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
        String sql = "INSERT INTO equipos (nombre, anio_fundacion, id_estadio) VALUES (?, ?, ?)";
        
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getAnioFundacion());
            ps.setInt(3, e.getIdEstadio());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
