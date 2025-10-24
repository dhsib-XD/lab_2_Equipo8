/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2;

import java.util.Calendar;

/**
 *
 * @author Hp
 */


public class EmpleadoVentas extends Empleado {

    private double[] VentasMensuales = new double[12];
    private double TasaComision;
    
    public EmpleadoVentas(int codigo, String nombre, Calendar fechacontra, double salariobase, double TasaComision) {
        super(codigo, nombre, fechacontra, salariobase);
        this.TasaComision = Math.max(0.0, TasaComision);
        
        for (int i = 0; i < VentasMensuales.length; i++) {
            VentasMensuales[i] = 0.0;
        }
    }
    
    private int IndiceMesActual() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    
    public boolean RegistrarVentaMesActual(double monto) {
        if (monto < 0) {
            return false;
        }
        
        int indice = IndiceMesActual();
        VentasMensuales[indice] += monto;
        return true;
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

    public double getTasaComision() {
        return TasaComision;
    }

    public void setTasaComision(double TasaComision) {
        this.TasaComision = TasaComision;
    }

    public double[] getVentasMensuales() {
        double[] copiaventa = new double[VentasMensuales.length];
        
        for (int i = 0; i < VentasMensuales.length; i++) {
            copiaventa[i] = VentasMensuales[i];
        }
        
        return copiaventa;
    }
    
    @Override
    public String MostrarInfo() {
        return super.MostrarInfo() + " | Tipo: Ventas | Comision mes: " + CalcularComisionMesActual() + " | Ventas año: " + CalcularVentasAnuales() + " | Pago: " + CalcularPagoMensual();
    }
}