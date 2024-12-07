/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafica;

import java.awt.BasicStroke;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Grafica {
        private XYSeries serieSusceptibles = new XYSeries("Susceptibles");
        private  XYSeries serieExpuestos = new XYSeries("Expuestos");
        private  XYSeries serieInfectados = new XYSeries("Infectados");
        private XYSeries serieRecuperados = new XYSeries("Recuperados");
        private  XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        private  JFreeChart chart;
        
    public  JPanel crearGrafico(double[][] resultados) {
        serieSusceptibles.clear();
        serieExpuestos.clear();
        serieInfectados.clear();
        serieRecuperados.clear();
        for (int i = 0; i < resultados.length; i++) {
            double tiempo = resultados[i][0]; 
            serieSusceptibles.add(tiempo, resultados[i][1]); // Susceptibles
            serieExpuestos.add(tiempo, resultados[i][2]);    // Expuestos
            serieInfectados.add(tiempo, resultados[i][3]);   // Infectados
            serieRecuperados.add(tiempo, resultados[i][4]);  // Recuperados
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serieSusceptibles);
        dataset.addSeries(serieExpuestos);
        dataset.addSeries(serieInfectados);
        dataset.addSeries(serieRecuperados);

        chart = ChartFactory.createXYLineChart(
                "Evolución del Modelo SEIR en 27 Semanas.", 
                "Tiempo (días)",           
                "Número de Personas",       
                dataset                     
        );
   
        renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f)); // Serie 0 (Susceptibles)
        renderer.setSeriesStroke(1, new BasicStroke(2.0f)); // Serie 1 (Expuestos)
        renderer.setSeriesStroke(2, new BasicStroke(2.0f)); // Serie 2 (Infectados)
        renderer.setSeriesStroke(3, new BasicStroke(2.0f)); // Serie 3 (Recuperados)
    
        chart.getXYPlot().setRenderer(renderer);

         chart.getPlot().setBackgroundPaint(java.awt.Color.GRAY);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(500, 400));

        // Crear y configurar la ventana
        //JPanel panelGrafica = new JPanel();
        //panelGrafica.add(panel, new BorderLayout().CENTER);
        //panelGrafica.setPreferredSize(new java.awt.Dimension(500, 400));

        // Retornar la ventana para mostrar o personalizar
        return panel;
    }
     public  void mostrarSoloSerie(int serieIndex) {
        for (int i = 0; i < 4; i++) { 
            renderer.setSeriesVisible(i, i == serieIndex);
            chart.getXYPlot().getDomainAxis().setAutoRange(true);
            chart.getXYPlot().getRangeAxis().setAutoRange(true);
        }
    }
     
     public  JPanel crearGraficoReal(double[][] datos) {
         XYSeries serieSusceptibles2 = new XYSeries("Susceptibles");
         XYSeries serieExpuestos2 = new XYSeries("Expuestos");
         XYSeries serieInfectados2 = new XYSeries("Infectados");
         XYSeries serieRecuperados2 = new XYSeries("Recuperados");
        
        
         
    for (int i = 0; i < datos.length; i++) {
        double semana = i; 
        serieSusceptibles2.add(semana, datos[i][0]);
        System.out.println(datos[i][0]);
        serieExpuestos2.add(semana, datos[i][1]);
        serieInfectados2.add(semana, datos[i][2]);
        serieRecuperados2.add(semana, datos[i][3]);
    }

    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(serieSusceptibles2);
    dataset.addSeries(serieExpuestos2);
    dataset.addSeries(serieInfectados2);
    dataset.addSeries(serieRecuperados2);

    JFreeChart chart2 = ChartFactory.createXYLineChart(
        "Datos Reales en 27 Semanas", 
        "Timepo(días).",                          
        "Número de Personas",                
        dataset                              
    );

    XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, false);
    chart2.getXYPlot().setRenderer(renderer2);

    ChartPanel panel = new ChartPanel(chart2);
    panel.setPreferredSize(new java.awt.Dimension(500, 400));

    return panel;
}
     
     public void mostrarTodo() {
        for (int i = 0; i < 4; i++) { 
            renderer.setSeriesVisible(i,true);
             chart.getXYPlot().getDomainAxis().setAutoRange(true);
            chart.getXYPlot().getRangeAxis().setAutoRange(true);
        }
     }
}

