/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.laliga.vista;

import com.laliga.dao.EquipoDAO;
import com.laliga.dao.EstadisticaDAO;
import com.laliga.dao.JugadorDAO;
import com.laliga.modelo.Equipo;
import com.laliga.modelo.Estadistica;
import com.laliga.modelo.Jugador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author leire.domsan
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private JTable tablaEquipos;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloJugadores;
    private JTable tablaJugadores;
    private DefaultTableModel modeloEstadisticas;
    private JTable tablaEstadisticas;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();

        // ==========================================
        // PESTAÑA 1: EQUIPOS
        // ==========================================
        jPanel1.setLayout(new java.awt.BorderLayout());
        // 1. Configuración básica de la ventana
        setTitle("Gestor de La Liga - 1º DAM");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla

        // 2. Crear el modelo de la tabla (las columnas)
        String[] columnas = {"ID", "Nombre del Equipo", "Año Fundación", "ID Estadio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEquipos = new JTable(modeloTabla);

        // 3. Añadir la tabla a un ScrollPane (para que tenga barra de desplazamiento)
        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        jPanel1.add(scrollPane, java.awt.BorderLayout.CENTER);

        cargarDatosRealesEquipos();

        // 5. Crear un panel para agrupar los botones
        JPanel panelBotones = new JPanel();
        JButton btnAnadir = new JButton("Añadir Equipo");
        JButton btnBorrar = new JButton("Borrar Equipo");

        // Añadimos los botones al panel
        panelBotones.add(btnAnadir);
        panelBotones.add(btnBorrar);

        // Añadimos el panel a la parte inferior (SOUTH) de la ventana principal
        jPanel1.add(panelBotones, java.awt.BorderLayout.SOUTH);

        // 6. Darle vida a los botones (ActionListeners)
        // Evento para el botón Añadir
        btnAnadir.addActionListener(e -> {
            FormularioEquipo form = new FormularioEquipo(this);
            form.setVisible(true);
            cargarDatosRealesEquipos(); // Refrescar equipos automáticamente
        });

        // Evento para el botón Borrar
        btnBorrar.addActionListener(e -> {

            int filaSeleccionada = tablaEquipos.getSelectedRow();

            if (filaSeleccionada != -1) {
                // 1. Conseguimos el ID real del equipo (columna 0)
                int idEquipo = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                String nombreEquipo = modeloTabla.getValueAt(filaSeleccionada, 1).toString();

                // Preguntar antes de romper nada
                int respuesta = JOptionPane.showConfirmDialog(this,
                        "¿Seguro que deseas eliminar al equipo '" + nombreEquipo + "' de la Base de Datos?",
                        "Confirmar Borrado Real", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    EquipoDAO dao = new EquipoDAO();

                    // 2. Llamamos a tu método eliminar del DAO
                    if (dao.eliminar(idEquipo)) {
                        JOptionPane.showMessageDialog(this, "Equipo eliminado correctamente.");
                        // 3. Volvemos a pintar la tabla leyendo de la BD ya limpia
                        cargarDatosRealesEquipos();
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "No se pudo eliminar.\nRecuerda que si el equipo tiene jugadores asociados en la base de datos, no se permite.",
                                "Error de Clave Foranea", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un equipo de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        });

        // ==========================================
        // PESTAÑA 2: JUGADORES
        // ==========================================
        jPanel2.setLayout(new java.awt.BorderLayout());

        // 1. Crear el modelo de la tabla de Jugadores (Añadimos "Nacionalidad" a las columnas)
        String[] colJugadores = {"ID", "Nombre", "Posición", "Dorsal", "Nacionalidad", "ID Equipo"};
        modeloJugadores = new DefaultTableModel(colJugadores, 0);
        tablaJugadores = new JTable(modeloJugadores);

        // 2. Añadir la tabla al panel central de la pestaña
        JScrollPane scrollJugadores = new JScrollPane(tablaJugadores);
        jPanel2.add(scrollJugadores, java.awt.BorderLayout.CENTER);

        // 3. Crear botones
        JPanel panelBotonesJug = new JPanel();
        JButton btnAnadirJugador = new JButton("Fichar Jugador");
        JButton btnBorrarJugador = new JButton("Despedir Jugador");
        JButton btnActualizarJugador = new JButton("Guardar Cambios"); // NUEVO BOTÓN

        panelBotonesJug.add(btnAnadirJugador);
        panelBotonesJug.add(btnBorrarJugador);
        panelBotonesJug.add(btnActualizarJugador); // LO AÑADO AL PANEL
        jPanel2.add(panelBotonesJug, java.awt.BorderLayout.SOUTH);

        cargarDatosRealesJugadores();

        // 5. EVENTO ACTUALIZADO: Abrir el formulario real
        btnAnadirJugador.addActionListener(e -> {
            // Abrimos el formulario que acabamos de crear
            FormularioJugador form = new FormularioJugador(this);
            form.setVisible(true);
            cargarDatosRealesJugadores();
        });

        // Evento para borrar
        btnBorrarJugador.addActionListener(e -> {
            int fila = tablaJugadores.getSelectedRow();

            if (fila != -1) {
                // 1. Conseguimos el ID real del jugador (columna 0)
                int idJugador = Integer.parseInt(modeloJugadores.getValueAt(fila, 0).toString());
                String nombreJugador = modeloJugadores.getValueAt(fila, 1).toString();

                int respuesta = JOptionPane.showConfirmDialog(this,
                        "¿Seguro que deseas rescindir el contrato de " + nombreJugador + "?\nSe borraran tambien sus estadisticas.",
                        "Confirmar Despido Real", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    JugadorDAO dao = new JugadorDAO();

                    // 2. Llamamos a tu método eliminar con transacción
                    if (dao.eliminar(idJugador)) {
                        JOptionPane.showMessageDialog(this, "Jugador y estadisticas eliminados correctamente.");
                        // 3. Limpiamos y refrescamos las dos pestañas afectadas automáticamente
                        cargarDatosRealesJugadores();
                        cargarDatosRealesEstadisticas();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al intentar eliminar el jugador.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un jugador primero.", "Atencion", JOptionPane.WARNING_MESSAGE);
            }
        });

        // EVENTO DEFINITIVO: Actualizar TODOS los campos del jugador seleccionado
        btnActualizarJugador.addActionListener(e -> {

            //Si la celda sigue en modo edición, forzamos a que fije el texto
            if (tablaJugadores.isEditing()) {
                tablaJugadores.getCellEditor().stopCellEditing();
            }

            int fila = tablaJugadores.getSelectedRow();

            if (fila != -1) {
                try {
                    // 1. Sacamos TODOS los datos de la fila de la tabla gráfica
                    int idJugador = Integer.parseInt(modeloJugadores.getValueAt(fila, 0).toString());
                    String nuevoNombre = modeloJugadores.getValueAt(fila, 1).toString();
                    String nuevaPosicion = modeloJugadores.getValueAt(fila, 2).toString();
                    int nuevoDorsal = Integer.parseInt(modeloJugadores.getValueAt(fila, 3).toString());
                    String nuevaNacionalidad = modeloJugadores.getValueAt(fila, 4).toString();
                    int nuevoIdEquipo = Integer.parseInt(modeloJugadores.getValueAt(fila, 5).toString());

                    // 2. Ejecutamos el UPDATE en la BD actualizando todos los campos a la vez
                    String sqlUpdate = "UPDATE jugadores SET nombre = ?, posicion = ?, dorsal = ?, nacionalidad = ?, id_equipo = ? WHERE id_jugador = ?";

                    try (java.sql.Connection con = com.laliga.util.ConexionBD.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sqlUpdate)) {

                        ps.setString(1, nuevoNombre);
                        ps.setString(2, nuevaPosicion);
                        ps.setInt(3, nuevoDorsal);
                        ps.setString(4, nuevaNacionalidad);
                        ps.setInt(5, nuevoIdEquipo);
                        ps.setInt(6, idJugador); // El WHERE usa el ID

                        if (ps.executeUpdate() > 0) {
                            JOptionPane.showMessageDialog(this, "¡Datos del jugador actualizados con exito!", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
                            // 3. Refrescamos la tabla
                            cargarDatosRealesJugadores();
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error de formato: Asegúrate de que el Dorsal y el ID Equipo sean numeros enteros validos.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar en la BD: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un jugador de la tabla primero.", "Atencion", JOptionPane.WARNING_MESSAGE);
            }
        });

        // ==========================================
        // PESTAÑA 3: ESTADÍSTICAS
        // ==========================================
        jPanel3.setLayout(new java.awt.BorderLayout());

        // 1. Columnas basadas en tu clase Estadistica.java
        String[] colEstadisticas = {"Jugador", "Goles", "Asistencias", "T. Amarillas", "T. Rojas", "P. Titular", "P. Suplente"};
        modeloEstadisticas = new DefaultTableModel(colEstadisticas, 0);
        tablaEstadisticas = new JTable(modeloEstadisticas);

        // 2. Añadir la tabla con scroll
        JScrollPane scrollEstadisticas = new JScrollPane(tablaEstadisticas);
        jPanel3.add(scrollEstadisticas, java.awt.BorderLayout.CENTER);

        // 3. Panel de botones para acciones de estadísticas
        JPanel panelBotonesEst = new JPanel();
        JButton btnActualizar = new JButton("Actualizar Datos");
        JButton btnTopGoleadores = new JButton("Ver Top Goleadores");

        panelBotonesEst.add(btnActualizar);
        panelBotonesEst.add(btnTopGoleadores);
        jPanel3.add(panelBotonesEst, java.awt.BorderLayout.SOUTH);

        // 4. Cargar datos de prueba
        cargarDatosRealesEstadisticas();

        // EVENTO: Actualizar Datos de la Fila Seleccionada
        btnActualizar.addActionListener(e -> {
            // Si el usuario está escribiendo y no pulsó Enter, forzamos a guardar el texto
            if (tablaEstadisticas.isEditing()) {
                tablaEstadisticas.getCellEditor().stopCellEditing();
            }

            int filaSeleccionada = tablaEstadisticas.getSelectedRow();

            if (filaSeleccionada != -1) {
                try {
                    // 1. Obtener los datos actuales de la fila seleccionada en la tabla gráfica
                    String nombreJugador = modeloEstadisticas.getValueAt(filaSeleccionada, 0).toString();
                    int goles = Integer.parseInt(modeloEstadisticas.getValueAt(filaSeleccionada, 1).toString());
                    int asistencias = Integer.parseInt(modeloEstadisticas.getValueAt(filaSeleccionada, 2).toString());
                    int amarillas = Integer.parseInt(modeloEstadisticas.getValueAt(filaSeleccionada, 3).toString());
                    int rojas = Integer.parseInt(modeloEstadisticas.getValueAt(filaSeleccionada, 4).toString());
                    int titular = Integer.parseInt(modeloEstadisticas.getValueAt(filaSeleccionada, 5).toString());
                    int suplente = Integer.parseInt(modeloEstadisticas.getValueAt(filaSeleccionada, 6).toString());

                    // 2. Buscar el ID del jugador a través de su nombre
                    JugadorDAO jDao = new JugadorDAO();
                    List<Jugador> todosLosJugadores = jDao.obtenerTodos();
                    int idJugador = -1;
                    for (Jugador j : todosLosJugadores) {
                        if (j.getNombre().equals(nombreJugador)) {
                            idJugador = j.getIdJugador();
                            break;
                        }
                    }

                    if (idJugador != -1) {
                        // 3. Crear el objeto estadística con los nuevos datos modificados de la tabla
                        com.laliga.modelo.Estadistica est = new com.laliga.modelo.Estadistica();
                        est.setIdJugador(idJugador);
                        est.setGoles(goles);
                        est.setAsistencias(asistencias);
                        est.setTarjetasAmarillas(amarillas);
                        est.setTarjetasRojas(rojas);
                        est.setPartidosTitular(titular);
                        est.setPartidosSuplente(suplente);
                        est.setPartidosSinJugar(0); // Valor por defecto

                        // 4. Guardar los cambios usando una actualización en la BD
                        EstadisticaDAO eDao = new EstadisticaDAO();

                        // Añadimos una consulta directa de actualización segura
                        String sqlUpdate = "UPDATE estadisticas SET goles=?, asistencias=?, tarjetas_amarillas=?, tarjetas_rojas=?, partidos_titular=?, partidos_suplente=? WHERE id_jugador=?";
                        try (java.sql.Connection con = com.laliga.util.ConexionBD.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
                            ps.setInt(1, est.getGoles());
                            ps.setInt(2, est.getAsistencias());
                            ps.setInt(3, est.getTarjetasAmarillas());
                            ps.setInt(4, est.getTarjetasRojas());
                            ps.setInt(5, est.getPartidosTitular());
                            ps.setInt(6, est.getPartidosSuplente());
                            ps.setInt(7, est.getIdJugador());

                            int filas = ps.executeUpdate();
                            if (filas > 0) {
                                JOptionPane.showMessageDialog(this, "¡Estadísticas de " + nombreJugador + " actualizadas en la Base de Datos!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }

                        // 5. Refrescar la tabla para asegurar la sincronización gráfica
                        cargarDatosRealesEstadisticas();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar: Asegúrate de introducir solo números enteros en los campos de estadísticas.\n" + ex.getMessage(), "Error de formato", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona primero la fila del jugador que deseas modificar en la tabla.", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        });

        // EVENTO: Mostrar Ranking Pichichi (Top Goleadores de la BD)
        btnTopGoleadores.addActionListener(e -> {
            StringBuilder ranking = new StringBuilder("🏆 --- TOP 5 MÁXIMOS GOLEADORES ---\n\n");
            String sqlTop = "SELECT j.nombre, e.goles FROM jugadores j "
                    + "JOIN estadisticas e ON j.id_jugador = e.id_jugador "
                    + "ORDER BY e.goles DESC LIMIT 5";

            try (java.sql.Connection con = com.laliga.util.ConexionBD.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sqlTop); java.sql.ResultSet rs = ps.executeQuery()) {

                int puesto = 1;
                while (rs.next()) {
                    ranking.append(puesto).append(". ")
                            .append(rs.getString("nombre")).append(" - ")
                            .append(rs.getInt("goles")).append(" goles\n");
                    puesto++;
                }

                // Desplegar el Top 5 en una ventana emergente muy visual
                JOptionPane.showMessageDialog(this, ranking.toString(), "Trofeo Pichichi", JOptionPane.INFORMATION_MESSAGE);

            } catch (java.sql.SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al consultar el ranking: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void cargarDatosRealesEquipos() {
        modeloTabla.setRowCount(0); // Limpiar tabla gráfica
        EquipoDAO dao = new EquipoDAO();
        List<Equipo> lista = dao.obtenerTodos();

        for (Equipo e : lista) {
            // Rellenamos las filas con los campos reales
            modeloTabla.addRow(new Object[]{
                e.getIdEquipo(),
                e.getNombre(),
                e.getAnio_fundacion(),
                e.getIdEstadio()
            });
        }
    }

    private void cargarDatosRealesJugadores() {
        modeloJugadores.setRowCount(0); // Limpiar tabla gráfica
        JugadorDAO dao = new JugadorDAO();
        List<Jugador> lista = dao.obtenerTodos();

        for (Jugador j : lista) {
            // Recuerda que modificamos la tabla para que tenga 6 columnas (con Nacionalidad)
            modeloJugadores.addRow(new Object[]{
                j.getIdJugador(),
                j.getNombre(),
                j.getPosicion(),
                j.getDorsal(),
                j.getNacionalidad(),
                j.getIdEquipo()
            });
        }
    }

    private void cargarDatosRealesEstadisticas() {
        modeloEstadisticas.setRowCount(0); // Limpiar tabla gráfica
        // Para la pestaña de estadísticas gráficas, podemos usar el jugadorDAO para listar los nombres
        // o hacer que lea directamente de la BD. Aquí tienes una carga limpia:
        JugadorDAO jDao = new JugadorDAO();
        EstadisticaDAO eDao = new EstadisticaDAO();
        List<Jugador> jugadores = jDao.obtenerTodos();

        for (Jugador j : jugadores) {
            Estadistica est = eDao.obtenerPorJugador(j.getIdJugador());
            if (est != null) {
                modeloEstadisticas.addRow(new Object[]{
                    j.getNombre(),
                    est.getGoles(),
                    est.getAsistencias(),
                    est.getTarjetasAmarillas(),
                    est.getTarjetasRojas(),
                    est.getPartidosTitular(),
                    est.getPartidosSuplente()
                });
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        equipos = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());
        equipos.addTab("Equipos", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );

        equipos.addTab("Jugadores", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );

        equipos.addTab("Estadísticas", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(equipos)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(equipos)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // ?1. Activamos el tema FlatLaf antes de arrancar nada
        try {
            // 1. Aplicamos el tema espectacular (Estilo Mac Oscuro)
            // Si prefieres color claro, cambia Dark por Light: com.formdev.flatlaf.themes.FlatMacLightLaf.setup();
            com.formdev.flatlaf.themes.FlatMacDarkLaf.setup();

            // 2. Ajustes extra para hacerlo súper moderno (Bordes redondeados)
            UIManager.put("Button.arc", 999); // Botones con forma de píldora
            UIManager.put("Component.arc", 15); // Bordes redondeados en paneles y tablas
            UIManager.put("TextComponent.arc", 15); // Bordes redondeados en campos de texto
            
        } catch( Exception ex ) {
            System.err.println( "Error al inicializar el tema FlatLaf" );
        }

        // 2. Textos en español para los paneles de confirmación
        UIManager.put("OptionPane.yesButtonText", "Sí");
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        // 3. Arrancamos tu ventana
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane equipos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
