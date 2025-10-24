package lab2;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author andre
 */
public class EmpresaMain extends JFrame {
      
    private Empresa lab2;

    private JTextField txtCodigo, txtNombre, txtSalario, txtHoras, txtMonto, txtComision;
    private JComboBox<String> cboTipo;
    private JSpinner spinnerFechaFin;
    private JButton btnRegistrar, btnHoras, btnVenta, btnContrato, btnReporte;

    public EmpresaMain() {
        lab2 = new Empresa();
        setTitle("Gestión de Empleados - Empresa G-8"); // G-8 porque somos el grupo 8
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("GESTIÓN DE EMPLEADOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 70, 140));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
        panelDatos.setBackground(new Color(230, 230, 230));

        txtCodigo = addLabeledTextField(panelDatos, "Código:");
        txtNombre = addLabeledTextField(panelDatos, "Nombre:");
        txtSalario = addLabeledTextField(panelDatos, "Salario base:");
        txtHoras = addLabeledTextField(panelDatos, "Horas trabajadas:");

        cboTipo = new JComboBox<>(new String[]{"Estándar", "Temporal", "Ventas"});
        JPanel tipoPanel = new JPanel(new BorderLayout(5, 5));
        tipoPanel.setBackground(new Color(230, 230, 230));
        tipoPanel.add(new JLabel("Tipo de empleado:"), BorderLayout.WEST);
        tipoPanel.add(cboTipo, BorderLayout.CENTER);
        panelDatos.add(tipoPanel);
        panelDatos.add(Box.createVerticalStrut(5));

        txtMonto = addLabeledTextField(panelDatos, "Monto de venta (solo ventas):");
        txtComision = addLabeledTextField(panelDatos, "Tasa comisión (solo ventas):");

        spinnerFechaFin = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerFechaFin, "dd/MM/yyyy");
        spinnerFechaFin.setEditor(editor);
        spinnerFechaFin.getEditor().getComponent(0).setBackground(Color.WHITE);

        JPanel fechaPanel = new JPanel(new BorderLayout(5, 5));
        fechaPanel.setBackground(new Color(230, 230, 230));
        fechaPanel.add(new JLabel("Fecha fin contrato (solo temporal):"), BorderLayout.WEST);
        fechaPanel.add(spinnerFechaFin, BorderLayout.CENTER);
        panelDatos.add(fechaPanel);

        add(panelDatos, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 5, 10, 10));
        panelBotones.setBackground(new Color(230, 230, 230));
        btnRegistrar = new JButton("Registrar");
        btnHoras = new JButton("Registrar Horas");
        btnVenta = new JButton("Registrar Venta");
        btnContrato = new JButton("Actualizar Contrato");
        btnReporte = new JButton("Generar Reporte");

        Color btnColor = new Color(70, 130, 180);
        btnRegistrar.setBackground(btnColor);
        btnHoras.setBackground(btnColor);
        btnVenta.setBackground(btnColor);
        btnContrato.setBackground(btnColor);
        btnReporte.setBackground(btnColor);
        btnRegistrar.setForeground(Color.WHITE);
        btnHoras.setForeground(Color.WHITE);
        btnVenta.setForeground(Color.WHITE);
        btnContrato.setForeground(Color.WHITE);
        btnReporte.setForeground(Color.WHITE);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnHoras);
        panelBotones.add(btnVenta);
        panelBotones.add(btnContrato);
        panelBotones.add(btnReporte);
        add(panelBotones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> registrarEmpleado());
        btnHoras.addActionListener(e -> lab2.registrarHoras(txtCodigo.getText(), Double.parseDouble(txtHoras.getText())));
        btnVenta.addActionListener(e -> lab2.registrarVenta(txtCodigo.getText(), Double.parseDouble(txtMonto.getText())));
        btnContrato.addActionListener(e -> {
            Date fecha = (Date) spinnerFechaFin.getValue();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            lab2.actualizarFinContrato(txtCodigo.getText(), cal);
        });
        btnReporte.addActionListener(e -> {
            String rep = lab2.reporte();
            JOptionPane.showMessageDialog(this, rep);
        });

        cboTipo.addActionListener(e -> actualizarVisibilidadCampos());
        actualizarVisibilidadCampos();
    }

    private JTextField addLabeledTextField(JPanel panel, String label) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBackground(new Color(230, 230, 230));
        JTextField txt = new JTextField();
        txt.setBackground(Color.WHITE);
        p.add(new JLabel(label), BorderLayout.WEST);
        p.add(txt, BorderLayout.CENTER);
        panel.add(p);
        panel.add(Box.createVerticalStrut(5));
        return txt;
    }

    private void actualizarVisibilidadCampos() {
        String tipo = (String) cboTipo.getSelectedItem();
        txtMonto.setEnabled(tipo.equals("Ventas"));
        txtComision.setEnabled(tipo.equals("Ventas"));
        spinnerFechaFin.setEnabled(tipo.equals("Temporal"));
    }

    private void registrarEmpleado() {
        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            double salario = Double.parseDouble(txtSalario.getText());
            String tipo = (String) cboTipo.getSelectedItem();
            Calendar hoy = Calendar.getInstance();
            boolean exito = false;
            if (tipo.equals("Estándar")) {
                exito = lab2.registrarEstandar(codigo, nombre, hoy, salario);
            } else if (tipo.equals("Temporal")) {
                Date fecha = (Date) spinnerFechaFin.getValue();
                Calendar finContrato = Calendar.getInstance();
                finContrato.setTime(fecha);
                exito = lab2.registrarTemporal(codigo, nombre, hoy, salario, finContrato);
            } else {
                double tasa = Double.parseDouble(txtComision.getText());
                exito = lab2.registrarVentas(codigo, nombre, hoy, salario, tasa);
            }
            if (exito) {
                JOptionPane.showMessageDialog(this, "Empleado registrado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error: código duplicado o datos incorrectos");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmpresaMain().setVisible(true));
    }
}
