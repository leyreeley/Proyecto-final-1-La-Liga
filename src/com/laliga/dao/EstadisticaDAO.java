/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.dao;

import com.laliga.modelo.Estadistica;
import com.laliga.util.ConexionBD;
import java.sql.*;
/**
 *
 * @author leire.domsan
 */
public class EstadisticaDAO {
    
    public boolean insertar(Estadistica est) {
        String sql = "INSERT INTO Estadisticas (id_jugador, goles, asistencias, "
                   + "tarjetas_amarillas, tarjetas_rojas, partidos_titular, "
                   + "partidos_suplente, partidos_sin_jugar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, est.getIdJugador());
            ps.setInt(2, est.getGoles());
            ps.setInt(3, est.getAsistencias());
            ps.setInt(4, est.getTarjetasAmarillas());
            ps.setInt(5, est.getTarjetasRojas());
            ps.setInt(6, est.getPartidosTitular());
            ps.setInt(7, est.getPartidosSuplente());
            ps.setInt(8, est.getPartidosSinJugar());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al insertar estadísticas: " + ex.getMessage());
            return false;
        }
    }
    
    public Estadistica obtenerPorJugador(int idJugador) {
        String sql = "SELECT * FROM Estadisticas WHERE id_jugador = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idJugador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Estadistica est = new Estadistica();
                    est.setIdEstadistica(rs.getInt("id_estadistica"));
                    est.setIdJugador(rs.getInt("id_jugador"));
                    est.setGoles(rs.getInt("goles"));
                    est.setAsistencias(rs.getInt("asistencias"));
                    est.setTarjetasAmarillas(rs.getInt("tarjetas_amarillas"));
                    est.setTarjetasRojas(rs.getInt("tarjetas_rojas"));
                    est.setPartidosTitular(rs.getInt("partidos_titular"));
                    est.setPartidosSuplente(rs.getInt("partidos_suplente"));
                    est.setPartidosSinJugar(rs.getInt("partidos_sin_jugar"));
                    return est;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener estadísticas: " + e.getMessage());
        }
        return null; // Si no tiene estadísticas registradas
    }

    // Usamos un JOIN para traer también el nombre del jugador
    public void mostrarTopGoleadores() {
        String sql = "SELECT j.nombre, e.goles FROM Jugadores j " +
                     "JOIN Estadisticas e ON j.id_jugador = e.id_jugador " +
                     "ORDER BY e.goles DESC LIMIT 5";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- TOP 5 GOLEADORES ---");
            int puesto = 1;
            while (rs.next()) {
                System.out.println(puesto + ". " + rs.getString("nombre") + " - " + rs.getInt("goles") + " goles");
                puesto++;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el Pichichi: " + e.getMessage());
        }
    }
}
