
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

/**
 *
 * @author andre
 */

import java.util.ArrayList;
import javax.swing.JFrame;

public class Empresa extends JFrame {
    private ArrayList<Empleado> Empleados = new ArrayList<>();
    
    
    
    public Empleado Buscar(String codigo) {
        if (codigo == null) {
            return null;
        }
        
        for (Empleado emp : Empleados) {
            if (codigo.equals(emp.codigo))
                return emp;
        }
        
        return null;
    }

    protected void registrarEmpleado() {
        
    }

    protected void registrarHoras() {
    }

    protected void registrarVenta() {
    }

    protected void actualizarFinContrato() {
    }
    public String generarReporte() {
    int estandar = 0, temporal = 0, ventas = 0;
    StringBuilder sb = new StringBuilder();

    sb.append("=== Reporte de Empleados ===\n\n");
    for (Empleado emp : Empleados) {
        sb.append(emp.MostrarInfo()).append("\n\n");

        switch (emp.tipoEmpleado) {  // Asegúrate de tener un atributo tipoEmpleado
            case "Estándar": estandar++; break;
            case "Temporal": temporal++; break;
            case "Ventas": ventas++; break;
        }
    }

    sb.append("--- Totales ---\n");
    sb.append("Empleados Estándar: ").append(estandar).append("\n");
    sb.append("Empleados Temporales: ").append(temporal).append("\n");
    sb.append("Empleados de Ventas: ").append(ventas).append("\n");
    sb.append("Total Empleados: ").append(Empleados.size()).append("\n");

    return sb.toString();
}


}

