package lab2;

import java.util.Calendar;
import java.util.Date;

public class EmpleadoTemporal extends Empresa {
        private String codigo;
        private String nombre;
        private double salarioBase;
        private double horasTrabajadas;
        private Calendar fechaContratacion;
        private Calendar fechaFinContrato;

        public EmpleadoTemporal(String codigo, String nombre, double salarioBase, double horasTrabajadas, Date fechaContratacion, Date fechaFin) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.salarioBase = salarioBase;
            this.horasTrabajadas = horasTrabajadas;

            this.fechaContratacion = Calendar.getInstance();
            this.fechaContratacion.setTime(fechaContratacion);

            this.fechaFinContrato = Calendar.getInstance();
            this.fechaFinContrato.setTime(fechaFin);
        }

        public double calcularPago() {
            Calendar hoy = Calendar.getInstance();
            if (!hoy.after(fechaFinContrato)) {
                double pago = salarioBase * (horasTrabajadas / 160.0);
                double deduccion = pago * 0.035;
                return pago - deduccion;
            } else {
                return 0;
            }
        }

        public String mostrarInformacion() {
            return "Código: " + codigo +
                   "\nNombre: " + nombre +
                   "\nSalario Base: $" + salarioBase +
                   "\nHoras Trabajadas: " + horasTrabajadas +
                   "\nPago Calculado: $" + calcularPago() +
                   "\nFecha Contratación: " + fechaContratacion.getTime() +
                   "\nFecha Fin Contrato: " + fechaFinContrato.getTime();
        }
        
public double calcularPagoMensual() {
    double pago = salarioBase * (horasTrabajadas / 160.0);
    return pago - pago * 0.035;
}
    }

    
