package Vista;

import Controller.EmpleadoController;
import DTO.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;

@Component
public class VistaBusquedaEmpleado extends JFrame {

    @Autowired
    private EmpleadoController empleadoController;

    private JTextField txtCodigo;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public VistaBusquedaEmpleado() {
        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Búsqueda de Empleado");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void agregarComponentes() {
        // Panel de busqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(new JLabel("Código de empleado:"));
        txtCodigo = new JTextField(10);
        panelBusqueda.add(txtCodigo);
        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);
        add(panelBusqueda, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"Código", "DNI", "Nombre", "Apellido", "Cargo", "Salario"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
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

        btnBuscar.addActionListener(e -> buscarEmpleado());
        btnEliminar.addActionListener(e -> eliminarEmpleado());
        btnLimpiar.addActionListener(e -> limpiar());
        btnRegresar.addActionListener(e -> dispose());
        btnModificar.addActionListener(e -> modificarEmpleado());
    }

    private void buscarEmpleado() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            Optional<EmpleadoDTO> resultado = empleadoController.buscarPorCodigodeEmpleado(codigo);

            modeloTabla.setRowCount(0);

            if (resultado.isPresent()) {
                EmpleadoDTO e = resultado.get();
                modeloTabla.addRow(new Object[]{
                    e.getCodigodeempleado(),
                    e.getDni(),
                    e.getNombre(),
                    e.getApellido(),
                    e.getCargo(),
                    String.format("S/ %.2f", e.getSalario())
                });
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se encontró ningún empleado con ese código.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El código debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEmpleado() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla primero.");
            return;
        }

        int codigo = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        Optional<EmpleadoDTO> empleado = empleadoController.buscarPorCodigodeEmpleado(codigo);

        if (empleado.isPresent()) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que deseas eliminar este empleado?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                empleadoController.eliminarEmpleado(empleado.get().getId());
                modeloTabla.removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(this, "Empleado eliminado ✅");
            }
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        modeloTabla.setRowCount(0);
    }

    private void modificarEmpleado() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla primero.");
            return;
        }

        int codigo = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        Optional<EmpleadoDTO> resultado = empleadoController.buscarPorCodigodeEmpleado(codigo);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el empleado seleccionado.");
            return;
        }

        EmpleadoDTO empleadoActual = resultado.get();

        JTextField txtNombre    = new JTextField(empleadoActual.getNombre());
        JTextField txtApellido  = new JTextField(empleadoActual.getApellido());
        JTextField txtTelefono  = new JTextField(empleadoActual.getTelefono());
        JTextField txtDireccion = new JTextField(empleadoActual.getDireccion());
        JTextField txtCargo     = new JTextField(empleadoActual.getCargo());
        JTextField txtSalario   = new JTextField(String.valueOf(empleadoActual.getSalario()));

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Nombre:"));    panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));  panel.add(txtApellido);
        panel.add(new JLabel("Teléfono:")); panel.add(txtTelefono);
        panel.add(new JLabel("Dirección:")); panel.add(txtDireccion);
        panel.add(new JLabel("Cargo:"));     panel.add(txtCargo);
        panel.add(new JLabel("Salario:"));   panel.add(txtSalario);

        int opcion = JOptionPane.showConfirmDialog(this, panel,
                "Modificar Empleado #" + codigo,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                empleadoActual.setNombre(txtNombre.getText());
                empleadoActual.setApellido(txtApellido.getText());
                empleadoActual.setTelefono(txtTelefono.getText());
                empleadoActual.setDireccion(txtDireccion.getText());
                empleadoActual.setCargo(txtCargo.getText());
                empleadoActual.setSalario(Double.parseDouble(txtSalario.getText()));

                empleadoController.registrarEmpleado(empleadoActual);

                JOptionPane.showMessageDialog(this, "Empleado actualizado correctamente ✅");
                buscarEmpleado();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "El salario debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error al actualizar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
