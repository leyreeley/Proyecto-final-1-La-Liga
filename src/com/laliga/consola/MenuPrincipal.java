/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.consola;

import com.laliga.dao.EstadisticaDAO;
import com.laliga.dao.JugadorDAO;
import com.laliga.modelo.Estadistica;
import com.laliga.modelo.Jugador;
import java.util.Scanner;

/**
 *
 * @author leire.domsan
 */
public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        EstadisticaDAO estadisticaDAO = new EstadisticaDAO();

        System.out.println("=========================================");
        System.out.println("    BIENVENIDO AL GESTOR DE LA LIGA ");
        System.out.println("=========================================");

        while (opcion != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Listar todos los Equipos");
            System.out.println("2. Alta de un nuevo Equipo");
            System.out.println("3. Listar todos los Jugadores");
            System.out.println("4. Fichar un Jugador");
            System.out.println("5. Ver Estadisticas de un Jugador");
            System.out.println("6. Ver Top Goleadores (Pichichi)");
            System.out.println("0. Salir del programa");
            System.out.print("Elige una opcion: ");

            try {
                // Leemos toda la línea y la convertimos a número
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--LISTADO DE EQUIPOS--");
                        // TODO: Llamar a equipoDAO.obtenerTodos()
                        System.out.println("- Real Valladolid (Mock)");
                        System.out.println("- Real Madrid (Mock)");
                        break;

                    case 2:
                        System.out.println("\n--ALTA NUEVO EQUIPO--");
                        System.out.print("Escribe el nombre del equipo: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Escribe el anio de fundacion: ");
                        int anio = Integer.parseInt(scanner.nextLine());
                        System.out.print("Escribe el ID del estadio: ");
                        int idEstadio = Integer.parseInt(scanner.nextLine());

                        // TODO: Llamar a equipoDAO.anadirEquipo(new Equipo(...))
                        System.out.println("Equipo '" + nombre + "' guardado correctamente.");
                        break;

                    case 3:
                        System.out.println("\n--LISTADO DE JUGADORES--");
                        // TODO: Llamar a jugadorDAO.obtenerTodos()
                        System.out.println("- Jordi Masip (Mock)");
                        System.out.println("- Vinícius Júnior (Mock)");
                        break;

                    case 4:
                        System.out.println("\n--FICHAR JUGADOR--");
                        System.out.print("Nombre del jugador: ");
                        String nombreJugador = scanner.nextLine();
                        System.out.print("Posicion (ej. Delantero): ");
                        String posicion = scanner.nextLine();
                        System.out.print("Dorsal: ");
                        System.out.print("Nacionalidad: ");
                        String nacionalidad = scanner.nextLine();
                        int dorsal = Integer.parseInt(scanner.nextLine());
                        System.out.print("ID del Equipo al que va: ");
                        int idEquipo = Integer.parseInt(scanner.nextLine());

                        Jugador j = new Jugador();
                        j.setNombre(nombreJugador);
                        j.setPosicion(posicion);
                        j.setNacionalidad(nacionalidad);
                        j.setDorsal(dorsal);
                        j.setIdEquipo(idEquipo);

                        // Llamar al DAO para que lo guarde de verdad
                        JugadorDAO jDAO = new JugadorDAO();
                        if (jDAO.insertar(j)) {
                            System.out.println("Jugador fichado correctamente en la base de datos.");
                        } else {
                            System.out.println("Error al guardar el jugador.");
                        }
                        break;

                    case 5:
                        System.out.println("\n--ESTADÍSTICAS DETALLADAS--");
                        System.out.print("Introduce el ID del jugador: ");
                        int idBuscado = Integer.parseInt(scanner.nextLine());

                        // Llamamos al DAO para buscar al jugador real
                        Estadistica estDetalle = estadisticaDAO.obtenerPorJugador(idBuscado);

                        if (estDetalle != null) {
                            System.out.println("Goles: " + estDetalle.getGoles());
                            System.out.println("Asistencias: " + estDetalle.getAsistencias());
                            System.out.println("Tarjetas Amarillas: " + estDetalle.getTarjetasAmarillas());
                            System.out.println("Partidos Titular: " + estDetalle.getPartidosTitular());
                            System.out.println("Partidos Suplente: " + estDetalle.getPartidosSuplente());
                        } else {
                            System.out.println("No se encontraron estadísticas para el ID: " + idBuscado);
                        }
                        break;

                    case 6:
                        System.out.println("\n--TOP GOLEADORES--");
                        estadisticaDAO.mostrarTopGoleadores();
                        break;

                    case 0:
                        System.out.println("\nSaliendo del sistema...");
                        break;

                    default:
                        System.out.println("\nOpcion no valida. Por favor, elige un numero del 0 al 6.");
                }

            } catch (NumberFormatException e) {
                // Esto evita que el programa "explote" si el usuario escribe letras en vez de un número
                System.out.println("\nError: Debes introducir un numero valido.");
            }
        }

        scanner.close();
    }
}
