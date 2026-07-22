package Vista;

import Controller.ClienteController;
import Controller.CuentaController;
import DTO.ClienteDTO;
import DTO.CuentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Optional;
import Modelo.TipodeCuenta;

@Component
public class VistaAbrirCuenta extends JFrame {

    @Autowired
    private CuentaController cuentaController;

    @Autowired
    private ClienteController clienteController;

    private JTextField txtCodigoCuenta;
    private JTextField txtCodigoCliente;
    private JComboBox<TipodeCuenta> cbTipoCuenta;
    private JTextField txtSaldoInicial;

    public VistaAbrirCuenta() {
        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Abrir Cuenta");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 5, 5));
    }

    private void agregarComponentes() {
        txtCodigoCuenta  = agregarCampo("Código de cuenta:");
        txtCodigoCliente = agregarCampo("Código del cliente:");

        JPanel panelTipo = new JPanel(new BorderLayout());
        panelTipo.add(new JLabel("Tipo de cuenta:"), BorderLayout.NORTH);
        cbTipoCuenta = new JComboBox<>(TipodeCuenta.values());
        panelTipo.add(cbTipoCuenta, BorderLayout.CENTER);
        add(panelTipo);

        txtSaldoInicial = agregarCampo("Saldo inicial:");

        JPanel panelBotones = new JPanel();
        JButton btnAbrir   = new JButton("Abrir Cuenta");
        JButton btnLimpiar = new JButton("Limpiar");

        btnAbrir.addActionListener(e -> abrirCuenta());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnAbrir);
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

    private void abrirCuenta() {
        try {
            int codigoCliente = Integer.parseInt(txtCodigoCliente.getText().trim());

            // Buscar el cliente por su codigo
            Optional<ClienteDTO> clienteOpt = clienteController.buscarPorCodigo(codigoCliente);
            if (clienteOpt.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No existe un cliente con código: " + codigoCliente,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CuentaDTO dto = new CuentaDTO();
            dto.setCodigodecuenta(Integer.parseInt(txtCodigoCuenta.getText().trim()));
            dto.setClienteId(clienteOpt.get().getId()); // usa el ID real internamente
            dto.setTipodeCuenta((TipodeCuenta) cbTipoCuenta.getSelectedItem());
            dto.setSaldo(Double.parseDouble(txtSaldoInicial.getText().trim()));
            dto.setFechadecreacion(LocalDate.now());
            dto.setEstado("ACTIVA");

            cuentaController.abrirCuenta(dto);

            JOptionPane.showMessageDialog(this,
                    "Cuenta abierta correctamente para el cliente: "
                    + clienteOpt.get().getNombre() + " " + clienteOpt.get().getApellido() + " ✅");
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Código de cuenta, código de cliente y saldo deben ser numéricos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigoCuenta.setText("");
        txtCodigoCliente.setText("");
        txtSaldoInicial.setText("");
        cbTipoCuenta.setSelectedIndex(0);
    }
}
