import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class EmpleadoTemporal extends JFrame {

    private JTextField txtCodigo, txtNombre, txtSalario, txtHoras;
    private JSpinner spiFechaContraracion, spinnerFechaFinContrato;

    public EmpleadoTemporal() {
        setTitle("Registro de Empleado Temporal");
        setSize(480, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Título
        JLabel Titulo = new JLabel("Registro de Empleado Temporal", JLabel.CENTER);
        Titulo.setFont(new Font("Arial", Font.BOLD, 18));
        Titulo.setBounds(40, 10, 400, 30);
        add(Titulo);

        // Etiquetas
        JLabel Codigo = new JLabel("Código:");
        Codigo.setBounds(50, 60, 120, 25);
        add(Codigo);

        JLabel Nombre = new JLabel("Nombre:");
        Nombre.setBounds(50, 100, 120, 25);
        add(Nombre);

        JLabel Salario = new JLabel("Salario Base:");
        Salario.setBounds(50, 140, 120, 25);
        add(Salario);

        JLabel Horas = new JLabel("Horas Trabajadas:");
        Horas.setBounds(50, 180, 120, 25);
        add(Horas);

        JLabel FechaContratacion = new JLabel("Fecha de Contratación:");
        FechaContratacion.setBounds(50, 220, 160, 25);
        add(FechaContratacion);

        JLabel fechafincontrato = new JLabel("Fecha Fin de Contrato:");
        fechafincontrato.setBounds(50, 260, 160, 25);
        add(fechafincontrato);

       
        txtCodigo = new JTextField();
        txtCodigo.setBounds(220, 60, 200, 25);
        add(txtCodigo);

        txtNombre = new JTextField();
        txtNombre.setBounds(220, 100, 200, 25);
        add(txtNombre);

        txtSalario = new JTextField();
        txtSalario.setBounds(220, 140, 200, 25);
        add(txtSalario);

        txtHoras = new JTextField();
        txtHoras.setBounds(220, 180, 200, 25);
        add(txtHoras);

        
        spiFechaContraracion = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor1 = new JSpinner.DateEditor(spiFechaContraracion, "dd/MM/yyyy");
        spiFechaContraracion.setEditor(dateEditor1);
        spiFechaContraracion.setBounds(220, 220, 200, 25);
        add(spiFechaContraracion);

        spinnerFechaFinContrato = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(spinnerFechaFinContrato, "dd/MM/yyyy");
        spinnerFechaFinContrato.setEditor(dateEditor2);
        spinnerFechaFinContrato.setBounds(220, 260, 200, 25);
        add(spinnerFechaFinContrato);

        // Botón registrar
        JButton btnRegistrar = new JButton("Registrar Empleado");
        btnRegistrar.setBounds(150, 310, 180, 35);
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarEmpleado());
    }

    
    private void registrarEmpleado() {
        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            double salario = Double.parseDouble(txtSalario.getText());
            double horas = Double.parseDouble(txtHoras.getText());

            Date fechaContratacion = (Date) spiFechaContraracion.getValue();
            Date fechaFin = (Date) spinnerFechaFinContrato.getValue();

            if (codigo.isEmpty() || nombre.isEmpty() || fechaContratacion == null || fechaFin == null) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            ET empleado = new ET(
                codigo, nombre, salario, horas, fechaContratacion, fechaFin
            );

            // Mostrar la información
            JOptionPane.showMessageDialog(this, empleado.mostrarInformacion(), "Empleado Registrado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clase interna representando un Empleado Temporal
    class ET {
        private String codigo;
        private String nombre;
        private double salarioBase;
        private double horasTrabajadas;
        private Calendar fechaContratacion;
        private Calendar fechaFinContrato;

        public ET(String codigo, String nombre, double salarioBase, double horasTrabajadas, Date fechaContratacion, Date fechaFin) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.salarioBase = salarioBase;
            this.horasTrabajadas = horasTrabajadas;

            this.fechaContratacion = Calendar.getInstance();
            this.fechaContratacion.setTime(fechaContratacion);

            this.fechaFinContrato = Calendar.getInstance();
            this.fechaFinContrato.setTime(fechaFin);
        }

        // Cálculo del pago condicionado
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

        // Mostrar información
        public String mostrarInformacion() {
            return "Código: " + codigo +
                   "\nNombre: " + nombre +
                   "\nSalario Base: $" + salarioBase +
                   "\nHoras Trabajadas: " + horasTrabajadas +
                   "\nPago Calculado: $" + calcularPago() +
                   "\nFecha Contratación: " + fechaContratacion.getTime() +
                   "\nFecha Fin Contrato: " + fechaFinContrato.getTime();
        }
    }
}
    
