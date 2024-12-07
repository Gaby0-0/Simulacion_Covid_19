package Modelo;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class ResolverEcuaciones implements FirstOrderDifferentialEquations {
    private double beta;
    private double sigma;
    private double gamma;
   

    public ResolverEcuaciones(double beta, double sigma, double gamma) {
        this.beta = beta;
        this.gamma = gamma;
        this.sigma = sigma; 
    }

    @Override
    public int getDimension() {
        return 4; // S, E, I, R
    }

    @Override
    public void computeDerivatives(double t, double[] y, double[] yDot) {
        double S = y[0]; // Susceptibles
        double E = y[1]; // Expuestos
        double I = y[2]; // Infectados
        double R = y[3]; // Recuperados
        System.out.println("elementos del grafico " +beta + " " + sigma+ " " + gamma);

        
        yDot[0] = -beta * S * I;    // Cambio en Susceptibles
        yDot[1] = beta * S * I - sigma * E;    // Cambio en Expuestos
        yDot[2] = sigma * E - gamma * I;    // Cambio en Infectados
        yDot[3] = gamma * I;    // Cambio en Recuperados
     
    }
       
}
