package lab2;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
/**
 *
 * @author andre
 */
public class EmpresaMain extends JFrame {

    private Empresa lab2;

    private JTextField txtCodigo, txtNombre, txtSalario, txtHoras, txtMonto, txtComision;
    private JComboBox<String> cboTipo;
    private JButton btnRegistrar, btnHoras, btnVenta, btnContrato, btnReporte;

    public EmpresaMain) {
        lab2 = new Empresa();
        setTitle("Gestión de Empleados - Empresa XYZ");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("GESTIÓN DE EMPLEADOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 102, 153));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(7, 2, 5, 5));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        panelCentral.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelCentral.add(txtCodigo);

        panelCentral.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCentral.add(txtNombre);

        panelCentral.add(new JLabel("Salario base:"));
        txtSalario = new JTextField();
        panelCentral.add(txtSalario);

        panelCentral.add(new JLabel("Tipo de empleado:"));
        cboTipo = new JComboBox<>(new String[]{"Estándar", "Temporal", "Ventas"});
        panelCentral.add(cboTipo);

        panelCentral.add(new JLabel("Horas trabajadas:"));
        txtHoras = new JTextField();
        panelCentral.add(txtHoras);

        panelCentral.add(new JLabel("Monto de venta (solo ventas):"));
        txtMonto = new JTextField();
        panelCentral.add(txtMonto);

        panelCentral.add(new JLabel("Tasa comisión (solo ventas, ej. 0.05):"));
        txtComision = new JTextField();
        panelCentral.add(txtComision);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 5, 5, 5));
        btnRegistrar = new JButton("Registrar Empleado");
        btnHoras = new JButton("Registrar Horas");
        btnVenta = new JButton("Registrar Venta");
        btnContrato = new JButton("Actualizar Contrato");
        btnReporte = new JButton("Generar Reporte");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnHoras);
        panelBotones.add(btnVenta);
        panelBotones.add(btnContrato);
        panelBotones.add(btnReporte);

        add(panelBotones, BorderLayout.SOUTH);

        // === EVENTOS ===
        btnRegistrar.addActionListener(e -> registrarEmpleado());
        btnHoras.addActionListener(e -> empresa.registrarHoras(txtCodigo.getText(), Double.parseDouble(txtHoras.getText())));
        btnVenta.addActionListener(e -> empresa.registrarVenta(txtCodigo.getText(), Double.parseDouble(txtMonto.getText())));
        btnContrato.addActionListener(e -> {
            Calendar nueva = new GregorianCalendar(2025, Calendar.DECEMBER, 31);
            empresa.actualizarFinContrato(txtCodigo.getText(), nueva);
        });
        btnReporte.addActionListener(e -> empresa.generarReporte());
    }

    private void registrarEmpleado() {
        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            double salario = Double.parseDouble(txtSalario.getText());
            String tipo = (String) cboTipo.getSelectedItem();

            if (tipo.equals("Estándar")) {
                empresa.registrarEmpleado(new Empleado(codigo, nombre, salario));
            } else if (tipo.equals("Temporal")) {
                Calendar fin = new GregorianCalendar(2025, Calendar.DECEMBER, 31);
                empresa.registrarEmpleado(new EmpleadoTemporal(codigo, nombre, salario, fin));
            } else {
                double tasa = Double.parseDouble(txtComision.getText());
                empresa.registrarEmpleado(new EmpleadoVentas(codigo, nombre, salario, tasa));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmEmpresa().setVisible(true));
    }

}
