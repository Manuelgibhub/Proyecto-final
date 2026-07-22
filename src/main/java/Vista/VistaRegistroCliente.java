/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
package Vista;

import Controller.ClienteController;
import DTO.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class VistaRegistroCliente extends JFrame {

    @Autowired
    private ClienteController clienteController;

    private JTextField txtCodigoCliente;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtOcupacion;

    public VistaRegistroCliente() {
        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Registro de clientes");
        setSize(400, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 5, 5));
    }

    private void agregarComponentes() {
        txtCodigoCliente = agregarCampo("Código de cliente:");
        txtDni = agregarCampo("DNI:");
        txtNombre = agregarCampo("Nombres:");
        txtApellido = agregarCampo("Apellido:");
        txtCorreo = agregarCampo("Correo:");
        txtTelefono = agregarCampo("Teléfono:");
        txtDireccion = agregarCampo("Dirección:");
        txtOcupacion = agregarCampo("Ocupación:");

        JPanel panelBotones = new JPanel();
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnRegistrar.addActionListener(e -> registrarCliente());
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

    private void registrarCliente() {
        try {
            ClienteDTO dto = new ClienteDTO();
            dto.setCodigodecliente(Integer.parseInt(txtCodigoCliente.getText()));
            dto.setDni(Integer.parseInt(txtDni.getText()));
            dto.setNombre(txtNombre.getText());
            dto.setApellido(txtApellido.getText());
            dto.setCorreo(txtCorreo.getText());
            dto.setTelefono(txtTelefono.getText());
            dto.setDireccion(txtDireccion.getText());
            dto.setOcupacion(txtOcupacion.getText());
            dto.setEstado(true);

            clienteController.regitrarCliente(dto);

            JOptionPane.showMessageDialog(this, "Cliente registrado correctamente ✅");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Código y DNI deben ser numéricos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigoCliente.setText("");
        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtOcupacion.setText("");
    }
}