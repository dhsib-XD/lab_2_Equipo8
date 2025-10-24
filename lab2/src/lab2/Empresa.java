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
import java.util.Calendar;

public class Empresa {
    private ArrayList<Empleado> Empleados = new ArrayList<>();

    public Empleado Buscar(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (Empleado emp : Empleados) {
            if (codigo.equals(emp.codigo)) {
                return emp;
            }
        }

        return null;
    }

    public boolean registrarEstandar(String codigo, String nombre, Calendar fechacontra, double salariobase) {
        if (codigo == null || codigo.isEmpty()) {
            return false;
        }

        if (Buscar(codigo) != null) {
            return false;
        }

        Empleado emp = new Empleado(codigo, nombre, fechacontra, salariobase);
        Empleados.add(emp);
        return true;
    }

    public boolean registrarVentas(String codigo, String nombre, Calendar fechacontra, double salariobase, double tasacomision) {
        if (codigo == null || codigo.isEmpty()) {
            return false;
        }

        if (Buscar(codigo) != null) {
            return false;
        }

        EmpleadoVentas empventas = new EmpleadoVentas(codigo, nombre, fechacontra, salariobase, tasacomision);
        Empleados.add(empventas);
        return true;
    }

    public boolean registrarTemporal(String codigo, String nombre, Calendar fechacontra, double salariobase, Calendar fincontrato) {
        if (codigo == null || codigo.isEmpty()) {
            return false;
        }

        if (Buscar(codigo) != null) {
            return false;
        }

        EmpleadoTemporal emptemp = new EmpleadoTemporal(codigo, nombre, salariobase, fechacontra, fincontrato);
        Empleados.add(emptemp);
        return true;
    }

    protected boolean registrarHoras(String codigo, double horas) {
        Empleado emp = Buscar(codigo);

        if (emp == null) {
            return false;
        }
        emp.Horastrabajadas(horas);
        return true;
    }

    protected boolean registrarVenta(String codigo, double monto) {
        Empleado emp = Buscar(codigo);
        if (emp == null) {
            return false;
        }
        ((EmpleadoVentas) emp).RegistrarVentaMesActual(monto);
        return true;
    }

    protected boolean actualizarFinContrato(String codigo, Calendar NuevaFecha) {
        Empleado emp = Buscar(codigo);

        if (emp == null) {
            return false;
        }
        if (!(emp instanceof EmpleadoTemporal)) {
            return false;
        }
        ((EmpleadoTemporal) emp).ActualizarFechaFinContrato(NuevaFecha);
        return true;
    }

    public String reporte() {
        String reporte = "==== REPORTE DE EMPLEADOS ===\n";
        int empest = 0;
        int empven = 0;
        int emptemp = 0;

        for (Empleado emp : Empleados) {
            if (emp instanceof EmpleadoTemporal) {
                reporte += ((EmpleadoTemporal) emp).mostrarInformacion() + "\n";
                emptemp++;
            } else if (emp instanceof EmpleadoVentas) {
                reporte += ((EmpleadoVentas) emp).MostrarInfo() + "\n";
                empven++;
            } else {
                reporte += emp.MostrarInfo() + " | Tipo: Estandar | Pago: " + emp.CalcularPagoMensual() + "\n";
                empest++;
            }
        }

        reporte += "\nTotales -> Estandar: " + empest + " | Temporales: " + emptemp + " | Ventas: " + empven;

        return reporte;
    }    
}