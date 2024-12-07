/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Comparativa;
import Vista.InformacionModelo;
import Vista.Municipios_Localidades;
import Vista.MasInformación;
import Vista.IngresarCSV;
import Modelo.LeerDatos;
import java.io.IOException;
import java.util.List;
import Modelo.ModeloSEIR;
import Grafica.Grafica;
import javax.swing.JPanel;
import Vista.TablaComparativa;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ControladorInicio {
    
    
   
    private Comparativa com = new Comparativa();
    private InformacionModelo info = new InformacionModelo();
    private Municipios_Localidades mun = new Municipios_Localidades();
    private MasInformación masinfo = new MasInformación();
    private IngresarCSV csv = new IngresarCSV();
    private static Grafica graf = new Grafica();
    private TablaComparativa t = new TablaComparativa();
    
    
   
    public void comparar( String selec) {
        com.setNombreArchivo(selec);
        com.setVisible(true);
        
    }
    
    public void comparar(String selec, double [] arr, int semanas){
        com.setNombreArchivoArreglo(selec, arr, semanas);
        com.setVisible(true);
    
    }
    
    public void infoModelo(){
        info.setVisible(true);
        
    }
    
    public void munLoc(){
        mun.setVisible(true);
    }
    
    public void masIn(){
        masinfo.setVisible(true);
    }
    
    public void ingCsv(){
        csv.setVisible(true);
    }
    public void tablac(){
    t.setVisible(true);
    }
    
 
   
    
    public static JPanel cargarGrafico(String nombre) throws IOException{
        System.out.println("este es el nombre " +nombre);
        LeerDatos dt = new LeerDatos();
        List<String[]> datos = dt.leeDatos("src/Modelo/"+nombre,1);
        
        double[]datos_nuevos = dt.convertirDatos(datos,1,3);
        double[]datos_nuevos2 = dt.convertirDatos(datos,2,3);
        ModeloSEIR modeloSeir = new ModeloSEIR(datos_nuevos[0],datos_nuevos[2], datos_nuevos2[2],datos_nuevos[3], datos_nuevos2[3],datos_nuevos[1]);
       // double[] y0 = {3974341, 6153, 150712, 942};
        double[][] resultados = modeloSeir.resolverModelo(datos_nuevos, 0, 189, 100);
        System.out.println("Tamaño:" + resultados.length);
        return graf.crearGrafico(resultados);
        
        }
        public static JPanel cargarGraficoArreglo(String nombre, double [] arrDatos, int semanas) throws IOException{
        System.out.println("este es el nombre " +nombre);
        LeerDatos dt = new LeerDatos();
        List<String[]> datos = dt.leeDatos("src/Modelo/"+nombre,1);
        
        double[]datos_nuevos = dt.convertirDatos(datos,1,3);
        double[]datos_nuevos2 = dt.convertirDatos(datos,2,3);
        ModeloSEIR modeloSeir = new ModeloSEIR(datos_nuevos[0],datos_nuevos[2], datos_nuevos2[2],datos_nuevos[3], datos_nuevos2[3],datos_nuevos[1]);
       // double[] y0 = {3974341, 6153, 150712, 942};
        double[][] resultados = modeloSeir.resolverModelo(arrDatos, 0, 7*semanas, 100);
        System.out.println("Tamaño:" + resultados.length);
        return graf.crearGrafico(resultados);
        
    }
    
     public  DefaultTableModel obtenerModeloTabla(String nombre)throws IOException {
         LeerDatos dt = new LeerDatos();
        List<String[]> datos =  dt.leeDatos("src/Modelo/"+nombre,1);
        String[] columnaNombres= datos.get(0);

        // Crear un modelo de tabla con los datos y las cabeceras
        DefaultTableModel model = new DefaultTableModel(columnaNombres, 0);

        for (int i = 1; i < datos.size(); i++) { // Comienza en el índice 1
        String[] fila = datos.get(i);
        model.addRow(fila);
    }

        // Retornar el modelo de la tabla
        return model;
    }
    
    
    public ArrayList<String> obtenerArchivos(){
        ArrayList <String> nombres = LeerDatos.getNombresArchivos("src/Modelo");
        
         return nombres;
    }
    
    
    
    public boolean validarCamposEnteros(JTextField[] campos) {
    for (JTextField campo : campos) {
        try {
            Integer.parseInt(campo.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Existen errores en alguno de los campos. Solo ingrese valores enteros. ", 
                                          "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;  
        }
    }
    return true;  
    }
    
    public static void mostrarUnaLinea(int indice){
        graf.mostrarSoloSerie(indice);
    }
   
    public static void revelarTodo(){
       graf.mostrarTodo();
    }
}
