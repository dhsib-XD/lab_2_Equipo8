/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package equipo8_lab2;

/**
 *
 * @author Hp
 */

import java.util.Calendar;
import lab2.Empleado;

public class EmpleadoVentas extends Empleado {

    private double[] VentasMensuales = new double[12];
    private double TasaComision;
    
    public EmpleadoVentas(String Codigo, String Nombre, Calendar FechaContratacion, double SalarioBase, double TasaComision) {
        super(Codigo, Nombre, FechaContratacion, SalarioBase);
        this.TasaComision = Math.max(0.0, TasaComision);
        
        for (int i = 0; i < VentasMensuales.length; i++) {
            VentasMensuales[i] = 0.0;
        }
    }
    
    private int IndiceMesActual() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    
    public void RegistrarVentaMesActual(double monto) {
        if (monto < 0) {
            System.out.println("El monto tiene que ser mayor a 0");
        }
        
        int indice = IndiceMesActual();
        VentasMensuales[indice] = (VentasMensuales[indice] + monto);
    }
    
    public double CalcularComisionMesActual() {
        int indice = IndiceMesActual();
        
        return VentasMensuales[indice] * TasaComision;
    }
    
    @Override
    public double CalcularPagoMensual() {
        return CalcularProporcional() + CalcularComisionMesActual();
    }
    
    public double CalcularVentasAnuales() {
        double total = 0.0;
        for (int i = 0; i < VentasMensuales.length; i++) {
            total += VentasMensuales[i];
        }
        
        return total;
    }
    
    
}