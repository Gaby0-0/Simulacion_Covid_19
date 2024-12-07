/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Grafica.Grafica;
import Modelo.LeerDatos;

import Vista.Inicio;
import java.io.IOException;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Controladorcomparativa {
    private static Grafica graf = new Grafica();
   

    private Inicio Inicio = new Inicio();
    
    public void inicio() {
        Inicio.setVisible(true);
    }
    
        public static JPanel cargarGraficoReal(String n) throws IOException{
        List<String[]> datos = LeerDatos.leeDatos("src/Modelo/"+n,2);
        System.out.println("es de comparativa "+ datos.get(0)[0]);
        double[][] resultados = LeerDatos.convertirDatosReales(datos, 3, 0);
        return graf.crearGraficoReal(resultados);
        }
        
       
}
