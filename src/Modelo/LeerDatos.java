/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;  // Importar la lista correcta

/**
 *
 * @author gabri
 */
public class LeerDatos {
    
    public static List<String[]> leeDatos(String archivo, int filaInicio) throws IOException {
        List<String[]> datos = new ArrayList<>();  // Usamos ArrayList como implementación de List

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int filaActual = 0;

            while ((linea = br.readLine()) != null) {
                filaActual++;

                if (filaActual >= filaInicio) {
   
                    String[] valores = linea.split(",");
                    datos.add(valores);
                    System.out.print("ooo" +Arrays.toString(valores));
                }
            }
        }
        return datos;
    }
    
    
      public static double[] convertirDatos(List<String[]> datos, int pos_fila, int pos_dato) {
          
        String[] fila = datos.get(pos_fila);
        double[] datos_convertidos = new double[4];
        int j = pos_dato-1;
        
        datos_convertidos[0] = Double.parseDouble(fila[j+3]);//Susceptibles
        datos_convertidos[1] = Double.parseDouble(fila[j+1]);//Expuestos
        datos_convertidos[2] = Double.parseDouble(fila[j]);//Infectados
        datos_convertidos[3] = Double.parseDouble(fila[j+2]);//Recuperados
        
        System.out.println();
        System.out.println(Arrays.toString(datos_convertidos));
       //  int j = pos_dato-1;
       // for (int i = 0; i<datos_convertidos.length; i++){
         //   System.out.println(Arrays.toString(fila));     
           // datos_convertidos[i]=Double.parseDouble(fila[j]);
             //System.out.println(datos_convertidos[i]);
             //j++;      
        //}
        
        return datos_convertidos;

        
    }
      public static double[][] convertirDatosReales(List<String[]> datos, int pos_dato, int pos_fila) {
        double[][] datos_convertidos = new double[datos.size()][4];  // Crear una matriz de 4 columnas para cada fila
    
    
        for (int i = pos_fila; i < datos.size(); i++) {
        String[] fila = datos.get(i); 
        int j = pos_dato - 1;  
        
        // Convertir y almacenar los 4 datos seleccionados para cada fila
        try {
            datos_convertidos[i][0] = Double.parseDouble(fila[j + 3]);
            datos_convertidos[i][1] = Double.parseDouble(fila[j + 1]);
            datos_convertidos[i][2] = Double.parseDouble(fila[j]);
            datos_convertidos[i][3] = Double.parseDouble(fila[j + 2]);
        } catch (NumberFormatException e) {
            // Si ocurre un error de conversión, asignar 0.0 o un valor predeterminado
            Arrays.fill(datos_convertidos[i],   0.0);
        }
        }

    // Imprimir la matriz de datos convertidos
    //System.out.println(Arrays.deepToString(datos_convertidos));

    return datos_convertidos;
       }
      
        public static ArrayList<String> getNombresArchivos(String directoryPath) {
        ArrayList<String> nombresArchivos = new ArrayList<>();

        File folder = new File(directoryPath);
        
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".csv")) {
                        nombresArchivos.add(file.getName()); 
                    }
                }
            }
        }
        return nombresArchivos;
    }

}
