/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controller.TransaccionController;
import DTO.TransaccionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import Modelo.TipodeTransaccion;

@Component
public class VistaTransaccion extends JFrame {

    @Autowired
    private TransaccionController transaccionController;

    private JTextField txtCodigoTransaccion;
    private JTextField txtCuentaId;
    private JComboBox<TipodeTransaccion> cbTipoTransaccion;
    private JTextField txtMonto;

    public VistaTransaccion() {
        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Realizar Transacción");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 5, 5));
    }

    private void agregarComponentes() {
        txtCodigoTransaccion = agregarCampo("Código de transacción:");
        txtCuentaId = agregarCampo("ID de cuenta:");

        JPanel panelTipo = new JPanel(new BorderLayout());
        panelTipo.add(new JLabel("Tipo de transacción:"), BorderLayout.NORTH);
        cbTipoTransaccion = new JComboBox<>(TipodeTransaccion.values());
        panelTipo.add(cbTipoTransaccion, BorderLayout.CENTER);
        add(panelTipo);

        txtMonto = agregarCampo("Monto:");

        JPanel panelBotones = new JPanel();
        JButton btnRealizar = new JButton("Realizar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnRealizar.addActionListener(e -> realizarTransaccion());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnRealizar);
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

    private void realizarTransaccion() {
        try {
            TransaccionDTO dto = new TransaccionDTO();
            dto.setCodigodeTransaccion(Integer.parseInt(txtCodigoTransaccion.getText()));
            dto.setCuentaId(Long.parseLong(txtCuentaId.getText()));
            dto.setTipodeTransaccion((TipodeTransaccion) cbTipoTransaccion.getSelectedItem());
            dto.setMonto(Double.parseDouble(txtMonto.getText()));
            dto.setFechadeTransaccion(LocalDate.now());

            transaccionController.registrarTransaccion(dto);

            JOptionPane.showMessageDialog(this, "Transacción realizada correctamente ✅");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Código, ID de cuenta y monto deben ser numéricos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigoTransaccion.setText("");
        txtCuentaId.setText("");
        txtMonto.setText("");
        cbTipoTransaccion.setSelectedIndex(0);
    }
}