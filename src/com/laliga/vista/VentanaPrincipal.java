/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.laliga.vista;

import com.laliga.modelo.Equipo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
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
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla

        // 2. Crear el modelo de la tabla (las columnas)
        String[] columnas = {"ID", "Nombre del Equipo", "Año Fundación", "ID Estadio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEquipos = new JTable(modeloTabla);

        // 3. Añadir la tabla a un ScrollPane (para que tenga barra de desplazamiento)
        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        jPanel1.add(scrollPane, java.awt.BorderLayout.CENTER);

        // 4. Cargar datos falsos (Mocking)
        cargarDatosFalsos();

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
            // Creamos el formulario pasándole esta ventana y el modelo de la tabla
            FormularioEquipo formulario = new FormularioEquipo(this, modeloTabla);
            // Lo hacemos visible
            formulario.setVisible(true);
        });

        // Evento para el botón Borrar
        btnBorrar.addActionListener(e -> {
            // Obtenemos qué fila ha seleccionado el usuario con el ratón
            int filaSeleccionada = tablaEquipos.getSelectedRow();

            if (filaSeleccionada != -1) {
                // Si hay una fila seleccionada, la borramos visualmente del modelo
                modeloTabla.removeRow(filaSeleccionada);
            } else {
                // Si no ha seleccionado nada, mostramos una advertencia
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

        panelBotonesJug.add(btnAnadirJugador);
        panelBotonesJug.add(btnBorrarJugador);
        jPanel2.add(panelBotonesJug, java.awt.BorderLayout.SOUTH);

        // 4. Cargar datos de prueba
        cargarDatosFalsosJugadores();

        // 5. EVENTO ACTUALIZADO: Abrir el formulario real
        btnAnadirJugador.addActionListener(e -> {
            // Abrimos el formulario que acabamos de crear
            FormularioJugador form = new FormularioJugador(this);
            form.setVisible(true);

            // Cuando se cierre el formulario, podrías llamar a un método para refrescar la tabla
            // actualizarTablaJugadores(); 
        });

        // Evento para borrar
        btnBorrarJugador.addActionListener(e -> {
            int fila = tablaJugadores.getSelectedRow();
            if (fila != -1) {
                modeloJugadores.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un jugador primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        });

        // ==========================================
        // PESTAÑA 3: ESTADÍSTICAS
        // ==========================================
        jPanel3.setLayout(new java.awt.BorderLayout());

        // 1. Columnas basadas en tu clase Estadistica.java
        String[] colEstadisticas = {"ID", "ID Jugador", "Goles", "Asistencias", "T. Amarillas", "T. Rojas"};
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
        cargarDatosFalsosEstadisticas();

        // 5. Eventos (preparados para la lógica posterior)
        btnActualizar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidad para modificar goles/tarjetas.");
        });

        btnTopGoleadores.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Aquí se mostraría el ranking de máximos goleadores (Pichichi).");
        });
    }

    private void cargarDatosFalsos() {
        // Simulamos que el DAO nos devuelve una lista de la base de datos
        List<Equipo> listaFalsa = new ArrayList<>();

        // Recorremos la lista y añadimos cada equipo como una fila en la tabla
        for (Equipo e : listaFalsa) {
            Object[] fila = {
                e.getIdEquipo(),
                e.getNombre(),
                e.getAnio_fundacion(),
                e.getIdEstadio()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void cargarDatosFalsosJugadores() {
        // Limpiamos el modelo por si acaso
        modeloJugadores.setRowCount(0);

        // Añadimos filas con los 6 datos (incluyendo la nacionalidad)
        // ID, Nombre, Posición, Dorsal, Nacionalidad, ID Equipo
        modeloJugadores.addRow(new Object[]{1, "Jordi Masip", "Portero", 1, "España", 1});
        modeloJugadores.addRow(new Object[]{2, "Vinícius Júnior", "Delantero", 7, "Brasil", 2});
        modeloJugadores.addRow(new Object[]{3, "Lamine Yamal", "Delantero", 19, "España", 3});
        modeloJugadores.addRow(new Object[]{4, "Kylian Mbappé", "Delantero", 9, "Francia", 2});
    }

    private void cargarDatosFalsosEstadisticas() {
        // Mocking de estadísticas
        modeloEstadisticas.addRow(new Object[]{1, 1, 0, 0, 1, 0}); // Masip (1 amarilla)
        modeloEstadisticas.addRow(new Object[]{2, 2, 15, 8, 3, 0}); // Vinícius (15 goles)
        modeloEstadisticas.addRow(new Object[]{3, 3, 5, 10, 0, 0}); // Lamine (10 asistencias)
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
