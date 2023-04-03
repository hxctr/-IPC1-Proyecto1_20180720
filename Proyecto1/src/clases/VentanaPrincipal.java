package clases;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton btnAdmonDeClientes, btnAdmonDeProductos, btnAdmonDeVentas, btnReportes;
    private JPanel panelVP;

    public VentanaPrincipal() {
        setSize(500, 500);
        setTitle("Ventana Principal");
        setLocationRelativeTo(null);
        iniciarComponentesVP();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void iniciarComponentesVP() {
        colocarPanelVP();
        colocarBotonesVP();
    }

    private void colocarPanelVP() {
        panelVP = new JPanel();
        panelVP.setLayout(null);
        this.add(panelVP);
    }

    public void colocarBotonesVP() {
        btnAdmonDeClientes = new JButton();
        btnAdmonDeClientes.setBounds(67, 80, 350, 30);
        btnAdmonDeClientes.setText("Administración de clientes");
        btnAdmonDeClientes.addActionListener(this);
        panelVP.add(btnAdmonDeClientes);

        btnAdmonDeProductos = new JButton();
        btnAdmonDeProductos.setBounds(67, 150, 350, 30);
        btnAdmonDeProductos.setText("Administración de productos");
        btnAdmonDeProductos.addActionListener(this);
        panelVP.add(btnAdmonDeProductos);

        btnAdmonDeVentas = new JButton();
        btnAdmonDeVentas.setBounds(67, 220, 350, 30);
        btnAdmonDeVentas.setText("Administración de ventas");
        btnAdmonDeVentas.addActionListener(this);
        panelVP.add(btnAdmonDeVentas);

        btnReportes = new JButton();
        btnReportes.setBounds(67, 290, 350, 30);
        btnReportes.setText("Reportes");
        btnReportes.addActionListener(this);
        panelVP.add(btnReportes);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnAdmonDeClientes) {
            btnAdmonClientesAccion();
        } else if (actionEvent.getSource() == btnAdmonDeProductos) {
            btnAdmonProductosAccion();
        } else if (actionEvent.getSource() == btnAdmonDeVentas) {
            btnAdmonVentasAccion();
        } else {
            btnReportesAccion();
        }
    }

    public void btnAdmonClientesAccion() {
        AdministracionDeClientes AC = new AdministracionDeClientes();
        AC.setVisible(true);
        this.dispose();
    }

    public void btnAdmonProductosAccion() {
        AdministracionDeProductos AP = new AdministracionDeProductos();
        AP.setVisible(true);
        this.dispose();
    }

    public void btnAdmonVentasAccion() {
        AdministracionDeVentas AV = new AdministracionDeVentas();
        AV.setVisible(true);
        this.dispose();
    }

    public void btnReportesAccion() {
        Reportes R = new Reportes();
        R.setVisible(true);
        this.dispose();
    }


}
