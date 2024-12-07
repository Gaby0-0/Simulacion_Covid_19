package Controlador;

import java.sql.SQLException;
import Conexion.CConexion;
import Modelo.LeerDatos;
import Vista.Inicio;
import Vista.TablaComparativa;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorTabla {

    private static Connection conn = CConexion.estableceConexion();
    private Inicio Inicio = new Inicio();

    public void inicio() {
        Inicio.setVisible(true);
    }

    // Retornar el modelo de la tabla
    public ArrayList<String> obtenerArchivos() {
        ArrayList<String> nombres = LeerDatos.getNombresArchivos("src/Modelo");
        return nombres;
    }

    public static ResultSet ListarTabla(String consulta) {
        Statement sql;
        ResultSet datos = null;
        try {
            sql = conn.createStatement();
            datos = sql.executeQuery(consulta);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.toString());
        }
        return datos;
    }

    public static void listarTablas() {
        DefaultTableModel md = new DefaultTableModel();
        ResultSet rs = ListarTabla("SELECT * FROM datostablas");
        md.setColumnIdentifiers(new Object[]{"Semana", "Fecha", "Infectados_Sim", "Expuestos_Sim", "Recuperados_Sim", "Susceptibles_Sim"});
        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("Semana"), rs.getString("Fecha"), rs.getString("Infectados"), rs.getString("Infectados_Sim"), rs.getString("Expuestos"), rs.getString("Expuestos_Sim"), rs.getString("Recuperados"), rs.getString("Recuperados_Sim"), rs.getString("Susceptibles"), rs.getString("Susceptibles_Sim")});
                TablaComparativa.TablaComparativaa.setModel(md);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.toString());
        }
    }

    public static DefaultTableModel buscarDatos(String buscar) {
        String[] nombreColumnas = {"Semana", "Fecha", "Infectados", "Infectados_Sim", "Expuestos", "Expuestos_Sim", "Recuperados", "Recuperados_Sim", "Susceptibles", "Susceptibles_Sim"};
        String[] registros = new String[10];
        DefaultTableModel modelo = new DefaultTableModel(null, nombreColumnas);
        String sql = "SELECT * FROM datostablas WHERE Semana LIKE '%" + buscar + "%' OR Fecha LIKE '%" + buscar + "%'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("Semana");
                registros[1] = rs.getString("Fecha");
                registros[2] = rs.getString("Infectados");
                registros[3] = rs.getString("Infectados_Sim");
                registros[4] = rs.getString("Expuestos");
                registros[5] = rs.getString("Expuestos_Sim");
                registros[6] = rs.getString("Recuperados");
                registros[7] = rs.getString("Recuperados_Sim");
                registros[8] = rs.getString("Susceptibles");
                registros[9] = rs.getString("Susceptibles_Sim");
                modelo.addRow(registros);
                TablaComparativa.TablaComparativaa.setModel(modelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return modelo;
    }

}
