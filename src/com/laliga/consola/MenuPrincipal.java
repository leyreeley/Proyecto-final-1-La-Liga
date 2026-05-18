/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laliga.consola;

import com.laliga.dao.EquipoDAO;
import com.laliga.dao.EstadisticaDAO;
import com.laliga.dao.JugadorDAO;
import com.laliga.modelo.Equipo;
import com.laliga.modelo.Estadistica;
import com.laliga.modelo.Jugador;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author leire.domsan
 */
public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        EquipoDAO equipoDAO = new EquipoDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();
        EstadisticaDAO estadisticaDAO = new EstadisticaDAO();
        int opcion = -1;

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
            System.out.println("7. Eliminar un Equipo");         
            System.out.println("8. Despedir (Eliminar) un Jugador"); 
            System.out.println("9. Cambiar dorsal de un Jugador");  
            System.out.println("0. Salir del programa");
            System.out.print("Elige una opcion: ");

            try {
                // Leemos toda la línea y la convertimos a número
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--LISTADO DE EQUIPOS--");
                        List<Equipo> equipos = equipoDAO.obtenerTodos();
                        if (equipos.isEmpty()) {
                            System.out.println("No hay equipos registrados.");
                        } else {
                            for (Equipo eq : equipos) {
                                System.out.println("ID: " + eq.getIdEquipo() + " | Name: " + eq.getNombre() + " | Año: " + eq.getAnio_fundacion());
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n--ALTA NUEVO EQUIPO--");
                        System.out.print("Escribe el nombre del equipo: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Escribe el anio de fundacion: ");
                        String anio = scanner.nextLine();
                        
                        // IMPORTANTE: Elige un ID de estadio que ya exista en el script de tu compañero (del 1 al 20)
                        System.out.print("Escribe el ID del estadio: ");
                        int idEstadio = Integer.parseInt(scanner.nextLine());

                        // El primer parámetro es 0 (porque el id_equipo en la BD es AUTO_INCREMENT y MySQL lo asigna solo)
                        Equipo nuevoEquipo = new Equipo(0, nombre, anio, idEstadio);
                        
                        if (equipoDAO.insertar(nuevoEquipo)) {
                            System.out.println("Equipo '" + nombre + "' guardado correctamente.");
                        } else {
                            System.out.println("Error al guardar el equipo.");
                        }
                        break;

                    case 3:
                        System.out.println("\n--LISTADO DE JUGADORES--");
                        List<Jugador> jugadores = jugadorDAO.obtenerTodos();
                        if (jugadores.isEmpty()) {
                            System.out.println("No hay jugadores registrados.");
                        } else {
                            for (Jugador jug : jugadores) {
                                System.out.println("ID: " + jug.getIdJugador() + " | " + jug.getNombre() + " (" + jug.getPosicion() + ") - Dorsal: " + jug.getDorsal() + " - Nac: " + jug.getNacionalidad());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n--FICHAR JUGADOR--");
                        System.out.print("Nombre del jugador: ");
                        String nombreJugador = scanner.nextLine();
                        System.out.print("Posicion (ej. Delantero): ");
                        String posicion = scanner.nextLine();
                        System.out.print("Dorsal: ");
                        int dorsal = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nacionalidad: ");
                        String nacionalidad = scanner.nextLine();
                        System.out.print("ID del Equipo al que va: ");
                        int idEquipo = Integer.parseInt(scanner.nextLine());

                        Jugador j = new Jugador();
                        j.setNombre(nombreJugador);
                        j.setPosicion(posicion);
                        j.setNacionalidad(nacionalidad);
                        j.setDorsal(dorsal);
                        j.setIdEquipo(idEquipo);

                        if (jugadorDAO.insertar(j)) {
                            System.out.println("Jugador fichado correctamente en la base de datos.");
                        } else {
                            System.out.println("Error al guardar el jugador.");
                        }
                        break;

                    case 5:
                        System.out.println("\n--ESTADISTICAS DETALLADAS--");
                        System.out.print("Introduce el ID del jugador: ");
                        int idBuscado = Integer.parseInt(scanner.nextLine());

                        // Llamamos al DAO para buscar al jugador real
                        Estadistica estDetalle = estadisticaDAO.obtenerPorJugador(idBuscado);

                        if (estDetalle != null) {
                            System.out.println("Goles: " + estDetalle.getGoles());
                            System.out.println("Asistencias: " + estDetalle.getAsistencias());
                            System.out.println("Tarjetas Amarillas: " + estDetalle.getTarjetasAmarillas());
                            System.out.println("Tarjetas Rojas: " + estDetalle.getTarjetasRojas());
                            System.out.println("Partidos Titular: " + estDetalle.getPartidosTitular());
                            System.out.println("Partidos Suplente: " + estDetalle.getPartidosSuplente());
                        } else {
                            System.out.println("No se encontraron estadisticas para el ID: " + idBuscado);
                        }
                        break;

                    case 6:
                        System.out.println("\n--TOP GOLEADORES--");
                        estadisticaDAO.mostrarTopGoleadores();
                        break;

                    case 7:
                        System.out.println("\n-- ELIMINAR EQUIPO --");
                        System.out.print("Introduce el ID del equipo a eliminar: ");
                        int idEqEliminar = Integer.parseInt(scanner.nextLine());
                        
                        if (equipoDAO.eliminar(idEqEliminar)) {
                            System.out.println("Equipo eliminado correctamente.");
                        } else {
                            System.out.println("No se pudo eliminar el equipo. Asegurate de que no tenga jugadores en plantilla.");
                        }
                        break;

                    case 8:
                        System.out.println("\n-- DESPEDIR JUGADOR --");
                        System.out.print("Introduce el ID del jugador a eliminar: ");
                        int idJugEliminar = Integer.parseInt(scanner.nextLine());
                        
                        if (jugadorDAO.eliminar(idJugEliminar)) {
                            System.out.println("Jugador y sus estadisticas eliminados correctamente.");
                        } else {
                            System.out.println("No se encontro ningun jugador con ese ID.");
                        }
                        break;

                    case 9:
                        System.out.println("\n-- CAMBIAR DORSAL --");
                        System.out.print("Introduce el ID del jugador: ");
                        int idJugDorsal = Integer.parseInt(scanner.nextLine());
                        System.out.print("Introduce el nuevo dorsal (1-99): ");
                        int nuevoDorsal = Integer.parseInt(scanner.nextLine());
                        
                        if (jugadorDAO.actualizarDorsal(idJugDorsal, nuevoDorsal)) {
                            System.out.println("Dorsal actualizado con exito.");
                        } else {
                            System.out.println("Error al actualizar. Comprueba el ID del jugador.");
                        }
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
