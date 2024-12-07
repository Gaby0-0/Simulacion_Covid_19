package Modelo;

import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;

public class ModeloSEIR {
    private double beta;
    private double gamma;
    private double sigma;
    private double S0, I0, I1, R0,  R1, E0;

    public ModeloSEIR(double S0, double I0, double I1, double R0, double R1, double E0) {
        this.S0 = S0;
        this.I0 = I0;
        this.I1 = I1;
        this.R0 = R0;
        this.R1 = R1;
        this.E0 = E0;
        
        calcularBeta();
        calcularGamma();
        calcularSigma();
        
    }

    public double[][] resolverModelo(double[]y0, double tInicio, double tFinal, int pasos) {
        double paso = (tFinal - tInicio) / pasos;
        FirstOrderIntegrator integrador = new DormandPrince853Integrator(1.0e-6, paso, 1.0e-6, 1.0e-6);
        ResolverEcuaciones ecuaciones = new ResolverEcuaciones(beta, sigma, gamma);

        double[][] resultados = new double[pasos + 1][5];
        double[] estado = y0.clone();
        double tiempo = tInicio;

        for (int i = 0; i <= pasos; i++) {
            resultados[i][0] = tiempo;
            System.arraycopy(estado, 0, resultados[i], 1, estado.length);
            tiempo += paso;
            integrador.integrate(ecuaciones, tiempo - paso, estado, tiempo, estado);
        }
        return resultados;
        
    }
  
        public void calcularBeta() {
        double deltaI = I1 - I0;
        beta = deltaI / (S0 * I0);
    }

    public void calcularGamma() {
        double deltaR = R1 - R0;
        gamma = deltaR / I0;
    }

    public void calcularSigma() {
        double deltaI = I1 - I0;
        sigma= deltaI / E0;
    }
  
    
}


