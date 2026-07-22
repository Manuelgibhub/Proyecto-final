/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controller.EmpleadoController;
import DTO.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class VistaRegistroEmpleado extends JFrame {

    @Autowired
    private EmpleadoController empleadoController;

    private JTextField txtCodigoEmpleado;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtCargo;
    private JTextField txtSalario;

    public VistaRegistroEmpleado() {
        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Registro de empleados");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 5, 5));
    }

    private void agregarComponentes() {
        txtCodigoEmpleado = agregarCampo("Código de empleado:");
        txtDni = agregarCampo("DNI:");
        txtNombre = agregarCampo("Nombres:");
        txtApellido = agregarCampo("Apellido:");
        txtTelefono = agregarCampo("Teléfono:");
        txtDireccion = agregarCampo("Dirección:");
        txtCargo = agregarCampo("Cargo:");
        txtSalario = agregarCampo("Salario:");

        JPanel panelBotones = new JPanel();
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnRegistrar.addActionListener(e -> registrarEmpleado());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        add(panelBotones);
    }

    private JTextField agregarCampo(String etiqueta) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(etiqueta), BorderLayout.NORTH);
        JTextField campo = new JTextField();
        panel.add(campo, BorderLayout.CENTER);
        add(panel);
        return campo;
    }

    private void registrarEmpleado() {
        try {
            EmpleadoDTO dto = new EmpleadoDTO();
            dto.setCodigodeempleado(Integer.parseInt(txtCodigoEmpleado.getText()));
            dto.setDni(Integer.parseInt(txtDni.getText()));
            dto.setNombre(txtNombre.getText());
            dto.setApellido(txtApellido.getText());
            dto.setTelefono(txtTelefono.getText());
            dto.setDireccion(txtDireccion.getText());
            dto.setCargo(txtCargo.getText());
            dto.setSalario(Double.parseDouble(txtSalario.getText()));

            empleadoController.registrarEmpleado(dto);

            JOptionPane.showMessageDialog(this, "Empleado registrado correctamente ✅");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Código, DNI y Salario deben ser numéricos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigoEmpleado.setText("");
        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCargo.setText("");
        txtSalario.setText("");
    }
}