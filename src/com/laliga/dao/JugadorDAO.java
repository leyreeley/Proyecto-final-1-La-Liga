/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.dao;

import com.laliga.modelo.Jugador;
import com.laliga.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leire.domsan
 */
public class JugadorDAO {

    // Listar todos los jugadores
    public List<Jugador> obtenerTodos() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setIdJugador(rs.getInt("id_jugador"));
                j.setNombre(rs.getString("nombre"));
                j.setPosicion(rs.getString("posicion"));
                j.setDorsal(rs.getInt("dorsal"));
                j.setIdEquipo(rs.getInt("id_equipo"));
                lista.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // LISTAR POR EQUIPO (Clave para la lógica de tu juego)
    public List<Jugador> obtenerPorEquipo(int idEquipo) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores WHERE id_equipo = ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEquipo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Jugador j = new Jugador();
                    j.setIdJugador(rs.getInt("id_jugador"));
                    j.setNombre(rs.getString("nombre"));
                    j.setPosicion(rs.getString("posicion"));
                    j.setDorsal(rs.getInt("dorsal"));
                    j.setNacionalidad(rs.getString("nacionalidad"));
                    j.setIdEquipo(rs.getInt("id_equipo"));
                    lista.add(j);
                    lista.add(j);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // INSERTAR (Fichar jugador)
    public boolean insertar(Jugador j) {
        // Añadimos "nacionalidad" a la consulta y un cuarto "?"
        String sql = "INSERT INTO jugadores (nombre, posicion, dorsal, nacionalidad, id_equipo) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, j.getNombre());
            ps.setString(2, j.getPosicion());
            ps.setInt(3, j.getDorsal());
            ps.setString(4, j.getNacionalidad()); // <--- NUEVO: Posición 4
            ps.setInt(5, j.getIdEquipo());       // <--- AHORA ES: Posición 5
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar jugador: " + ex.getMessage());
            return false;
        }
    }
}
