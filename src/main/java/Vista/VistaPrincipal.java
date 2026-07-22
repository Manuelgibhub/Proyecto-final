/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class VistaPrincipal extends JFrame {

    @Autowired
    private ApplicationContext context;

    public VistaPrincipal() {
        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Apex Bank");
        setSize(400, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
    }

    private void agregarComponentes() {
        JLabel titulo = new JLabel("Apex Bank", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(7, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelBotones.setBackground(Color.WHITE);

        JButton btnRegistrarCliente  = new JButton("Registrar Cliente");
        JButton btnBuscarCliente     = new JButton("Buscar Cliente");
        JButton btnAbrirCuenta       = new JButton("Abrir Cuenta");
        JButton btnTransaccion       = new JButton("Realizar Transacción");
        JButton btnRegistrarEmpleado = new JButton("Registrar Empleado");
        JButton btnBuscarEmpleado    = new JButton("Buscar Empleado");
        JButton btnSalir             = new JButton("Salir");
        btnSalir.setForeground(Color.RED);

        btnRegistrarCliente.addActionListener(e ->
                context.getBean(VistaRegistroCliente.class).setVisible(true));

        btnBuscarCliente.addActionListener(e ->
                context.getBean(VistaBusquedaCliente.class).setVisible(true));

        btnRegistrarEmpleado.addActionListener(e ->
                context.getBean(VistaRegistroEmpleado.class).setVisible(true));

        btnBuscarEmpleado.addActionListener(e ->
                context.getBean(VistaBusquedaEmpleado.class).setVisible(true));

        btnAbrirCuenta.addActionListener(e ->
                context.getBean(VistaAbrirCuenta.class).setVisible(true));

        btnTransaccion.addActionListener(e ->
                context.getBean(VistaTransaccion.class).setVisible(true));

        btnSalir.addActionListener(e -> System.exit(0));

        panelBotones.add(btnRegistrarCliente);
        panelBotones.add(btnBuscarCliente);
        panelBotones.add(btnAbrirCuenta);
        panelBotones.add(btnTransaccion);
        panelBotones.add(btnRegistrarEmpleado);
        panelBotones.add(btnBuscarEmpleado);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);
    }
}