/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.util.Calendar;

/**
 *
 * @author andrea
 */
public class Empleado {
    protected String codigo;
    String nombre;
    Calendar fechacontra;
    double salariobase;
    double horastrabajadas;

    public Empleado(String codigo, String nombre, Calendar fechacontra, double salariobase) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechacontra = Calendar.getInstance();
        this.salariobase = salariobase;
        horastrabajadas = 0;
    }

    public double Horastrabajadas(double horas) {
        if (horas > 0) {
            return horastrabajadas += horas;
        } else {
            return horastrabajadas = horastrabajadas;
        }

    }
    
    protected double CalcularProporcional() {
        return salariobase * (horastrabajadas / 160.0);
    }

    public double CalculoPago(double horastrabajadas) {
        double salarioproporcional = horastrabajadas / 160;
        double descuento = salarioproporcional * 0.035;
        return salarioproporcional - descuento;
    }

    public String MostrarInfo() {
        return "Codigo del empleado: " + codigo + " \nNombre del Empleado:" + nombre + "\nFecha de contratacion: " + fechacontra.getTime();

    }
    
    public double CalcularPagoMensual() {
        double proporcional = CalcularProporcional();
        double descuento = proporcional * 0.035;
        double neto = proporcional - descuento;
        
        return (neto > 0) ? 0.0 : neto;
    }
}
