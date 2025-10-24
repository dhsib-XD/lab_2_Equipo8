package lab2;

import java.util.Calendar;

public class EmpleadoTemporal extends Empleado {
        private String codigo;
        private String nombre;
        private double salarioBase;
        private double horasTrabajadas;
        private Calendar fechaContratacion;
        private Calendar fechaFinContrato;

        public EmpleadoTemporal(String codigo, String nombre, double salariobase, Calendar fechacontra, Calendar fechaFin) {
            super(codigo, nombre, fechacontra, salariobase);
            horasTrabajadas = 0;

            this.fechaFinContrato = Calendar.getInstance();
            this.fechaFinContrato.getTime();
            
            fechacontra.getTime();
        }

        public double calcularPago() {
            Calendar hoy = Calendar.getInstance();
            if (!hoy.after(fechaFinContrato)) {
                double pago = salariobase * (horasTrabajadas / 160.0);
                double deduccion = pago * 0.035;
                return pago - deduccion;
            } else {
                return 0;
            }
        }

        public String mostrarInformacion() {
            return "Código: " + codigo +
                   "\nNombre: " + nombre +
                   "\nSalario Base: $" + salariobase +
                   "\nHoras Trabajadas: " + horasTrabajadas +
                   "\nPago Calculado: $" + calcularPago() +
                   "\nFecha Contratación: " + fechaContratacion.getTime() +
                   "\nFecha Fin Contrato: " + fechaFinContrato.getTime();
        }
        
        public double calcularPagoMensual() {
            double pago = salarioBase * (horasTrabajadas / 160.0);
            return pago - pago * 0.035;
        }
        
        public void ActualizarFechaFinContrato(Calendar nuevafecha) {
            if (nuevafecha == null) {
                this.fechaFinContrato = Calendar.getInstance();
            } else {
                this.fechaContratacion.setTime(nuevafecha.getTime());
            }
        }
    }

    
