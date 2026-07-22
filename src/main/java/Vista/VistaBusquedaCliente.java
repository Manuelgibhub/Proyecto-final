package Vista;

import Controller.ClienteController;
import Controller.CuentaController;
import DTO.ClienteDTO;
import DTO.CuentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@Component
public class VistaBusquedaCliente extends JFrame {

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private CuentaController cuentaController;

    private JTextField txtCodigo;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public VistaBusquedaCliente() {
        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Búsqueda de Cliente");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void agregarComponentes() {
        // Panel de busqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(new JLabel("Código de cliente:"));
        txtCodigo = new JTextField(10);
        panelBusqueda.add(txtCodigo);
        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);
        add(panelBusqueda, BorderLayout.NORTH);

        // Tabla con columna de saldo total
        String[] columnas = {"Código", "DNI", "Nombre", "Apellido", "Estado", "Saldo Total"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar  = new JButton("Eliminar");
        JButton btnLimpiar   = new JButton("Limpiar");
        JButton btnRegresar  = new JButton("Regresar");
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRegresar);
        add(panelBotones, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnLimpiar.addActionListener(e -> limpiar());
        btnRegresar.addActionListener(e -> dispose());
        btnModificar.addActionListener(e -> modificarCliente());
    }

    private void buscarCliente() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            Optional<ClienteDTO> resultado = clienteController.buscarPorCodigo(codigo);

            modeloTabla.setRowCount(0);

            if (resultado.isPresent()) {
                ClienteDTO c = resultado.get();

                // Sumar saldo de todas las cuentas del cliente
                List<CuentaDTO> cuentas = cuentaController.buscarCuentasPorCliente(c.getId());
                double saldoTotal = cuentas.stream()
                        .mapToDouble(CuentaDTO::getSaldo)
                        .sum();

                modeloTabla.addRow(new Object[]{
                    c.getCodigodecliente(),
                    c.getDni(),
                    c.getNombre(),
                    c.getApellido(),
                    c.isEstado() ? "Activo" : "Inactivo",
                    String.format("S/ %.2f", saldoTotal)
                });
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se encontró ningún cliente con ese código.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El código debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCliente() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla primero.");
            return;
        }

        int codigo = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        Optional<ClienteDTO> cliente = clienteController.buscarPorCodigo(codigo);

        if (cliente.isPresent()) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que deseas eliminar este cliente?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                clienteController.eliminarCliente(cliente.get().getId());
                modeloTabla.removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(this, "Cliente eliminado ✅");
            }
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        modeloTabla.setRowCount(0);
    }

    private void modificarCliente() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla primero.");
            return;
        }

        int codigo = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        Optional<ClienteDTO> resultado = clienteController.buscarPorCodigo(codigo);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el cliente seleccionado.");
            return;
        }

        ClienteDTO clienteActual = resultado.get();

        JTextField txtNombre    = new JTextField(clienteActual.getNombre());
        JTextField txtApellido  = new JTextField(clienteActual.getApellido());
        JTextField txtCorreo    = new JTextField(clienteActual.getCorreo());
        JTextField txtTelefono  = new JTextField(clienteActual.getTelefono());
        JTextField txtDireccion = new JTextField(clienteActual.getDireccion());
        JTextField txtOcupacion = new JTextField(clienteActual.getOcupacion());
        JCheckBox  chkEstado    = new JCheckBox("Activo", clienteActual.isEstado());

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Nombre:"));    panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));  panel.add(txtApellido);
        panel.add(new JLabel("Correo:"));    panel.add(txtCorreo);
        panel.add(new JLabel("Teléfono:")); panel.add(txtTelefono);
        panel.add(new JLabel("Dirección:")); panel.add(txtDireccion);
        panel.add(new JLabel("Ocupación:")); panel.add(txtOcupacion);
        panel.add(chkEstado);

        int opcion = JOptionPane.showConfirmDialog(this, panel,
                "Modificar Cliente #" + codigo,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                clienteActual.setNombre(txtNombre.getText());
                clienteActual.setApellido(txtApellido.getText());
                clienteActual.setCorreo(txtCorreo.getText());
                clienteActual.setTelefono(txtTelefono.getText());
                clienteActual.setDireccion(txtDireccion.getText());
                clienteActual.setOcupacion(txtOcupacion.getText());
                clienteActual.setEstado(chkEstado.isSelected());

                clienteController.regitrarCliente(clienteActual);

                JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente ✅");
                buscarCliente();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error al actualizar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
