package clases;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AdministracionDeProductos extends JFrame implements ActionListener {

    private JPanel panelAP;
    private JButton btnCargaMasivaAP, btnDashboardAP, btnGraficaDePieAP, btnGraficaDeBarrasAP;

    CargaMasivaProductos[] datosProductos = new CargaMasivaProductos[300];
    private Object[][] columnas;

    public static int contadorProductos = 0, contador0a50 = 0, contador51a100 = 0, contador101a150 = 0,
                      contador151a200 = 0, contador201a1000 = 0;

    public AdministracionDeProductos() {
        setSize(900, 1000);
        setTitle("Administracion de productos");
        setLocationRelativeTo(null);
        iniciarComponentesAP();
        cerar();
    }

    private void cerar() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                VentanaPrincipal VP = new VentanaPrincipal();
                VP.setVisible(true);
                dispose();
            }
        });
    }

    private void iniciarComponentesAP() {
        colocarPanelAP();
        colocarBotonesAP();
        interfazCreacionProducto();
        interfazModificacionProducto();
        interfazVistaProducto();
        interfazEliminarProducto();
    }

    private void colocarPanelAP() {
        panelAP = new JPanel();
        panelAP.setLayout(null);
        this.add(panelAP);
    }

    private void colocarBotonesAP() {
        btnCargaMasivaAP = new JButton();
        btnCargaMasivaAP.setBounds(48, 60, 800, 30);
        btnCargaMasivaAP.setText("Carga masiva de productos");
        btnCargaMasivaAP.addActionListener(this);
        panelAP.add(btnCargaMasivaAP);

        btnDashboardAP = new JButton();
        btnDashboardAP.setBounds(48, 100, 800, 30);
        btnDashboardAP.setText("Dashboard de productos en el sistema");
        btnDashboardAP.addActionListener(this);
        panelAP.add(btnDashboardAP);

        btnGraficaDePieAP = new JButton();
        btnGraficaDePieAP.setBounds(225, 325, 200, 30);
        btnGraficaDePieAP.setText("Ver grafica de pie");
        btnGraficaDePieAP.addActionListener(this);
        panelAP.add(btnGraficaDePieAP);

        btnGraficaDeBarrasAP = new JButton();
        btnGraficaDeBarrasAP.setBounds(460, 325, 200, 30);
        btnGraficaDeBarrasAP.setText("Ver grafica de barras");
        btnGraficaDeBarrasAP.addActionListener(this);
        panelAP.add(btnGraficaDeBarrasAP);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnCargaMasivaAP) {
            btnCargaMasivaAPAccion();
        } else if (actionEvent.getSource() == btnDashboardAP) {
            btnDashboardAPAccion();
        } else if (actionEvent.getSource() == btnGraficaDeBarrasAP) {
            btnGraficaDeBarrasAPAccion();
        } else if (actionEvent.getSource() == btnGraficaDePieAP) {
            btnGRaficaDePieAPAccion();
        } else if (actionEvent.getSource() == btnImagenProducto) {
            btnImagenProductoAccion();
        }else if (actionEvent.getSource() == btnAniadirProducto){
            btnAniadirProductoAccion();
        }else if (actionEvent.getSource() == btnBuscarProducto){
            btnBuscarProdcuctoAccion();
        }else if (actionEvent.getSource() == btnBuscarProducto2){
            btnEliminarProductoAccion();
        }


    }


    private int k = 0;
    private int y = 0;
    private String[] spliteado;
    public static double[] matrizDePrecios = new double[300];
    public static String[] vectorDeProductos = new String[300];

    private void btnCargaMasivaAPAccion() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos .csv", "csv");
        fileChooser.addChoosableFileFilter(filtro);

        int operacion = fileChooser.showOpenDialog(this);
        if (operacion == JFileChooser.APPROVE_OPTION) {
            try {

                String locacion = fileChooser.getSelectedFile().toString();

                File fichero = fileChooser.getSelectedFile();
                FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr);

                String contenido;
                while ((contenido = br.readLine()) != null) {
                    spliteado = contenido.split(",");
                    String nombre = spliteado[0];
                    double precio = Double.parseDouble(spliteado[1]);

                    int cantidad = Integer.parseInt(spliteado[2]);
                    String imagen = spliteado[3];


                    //Creare un vector de precios
                    matrizDePrecios[k] = precio;
                    k++;
                    //Finaliza la creacion del vector de precios
                    vectorDeProductos[y] = nombre;
                    y++;
                    //vector de productos




                    //Estos if me sirven para hacer las graficas
                    if (precio >= 0 && precio <= 50) {
                        contador0a50++;
                    } else if (precio >= 51 && precio <= 100) {
                        contador51a100++;
                    } else if (precio >= 101 && precio <= 150) {
                        contador101a150++;
                    } else if (precio >= 151 && precio <= 200) {
                        contador151a200++;
                    } else if (precio >= 201 && precio <= 1000) {
                        contador201a1000++;
                    }
                    //Finalizan los if que me sirven para las graficas

                    datosProductos[contadorProductos] = new CargaMasivaProductos(nombre, precio, cantidad, imagen);
                    contadorProductos++;
                }
                JOptionPane.showMessageDialog(null, "El archivo .csv fue cargado correctamente");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void btnDashboardAPAccion() {
        columnas = new Object[contadorProductos][3];

        for (int i = 0; i < contadorProductos; i++) {
            columnas[i][0] = datosProductos[i].getNombre();
            columnas[i][1] = datosProductos[i].getPrecio();
            columnas[i][2] = datosProductos[i].getCantidad();
        }

        String[] encabezados = {"Nombre", "Precio", "Cantidad"};
        JTable tabla = new JTable(columnas, encabezados);
        tabla.setBounds(300, 200, 300, 100);
        panelAP.add(tabla);

        JScrollPane scroll = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(300, 200, 300, 100);
        panelAP.add(scroll);


    }

    private void btnGraficaDeBarrasAPAccion() {
        GraficaProductosPorPrecio example = new GraficaProductosPorPrecio("Gráfica de barras de cantidad de productos por rango de precios");
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        example.setVisible(true);
    }

    private void btnGRaficaDePieAPAccion() {
        GraficaProductosPorPrecioBarras example = new GraficaProductosPorPrecioBarras("ráfica de barras de cantidad de productos por rango de precios");
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        example.setVisible(true);
    }

    private JLabel lblNuevoProducto, lblNombreProducto, lblPrecioProducto, lblCantidadProducto, lblImagenProducto;
    private JTextField txtNombreProducto, txtPrecioProducto, txtCantidadProdcuto;
    private JButton btnImagenProducto, btnAniadirProducto;

    private void interfazCreacionProducto() {
        lblNuevoProducto = new JLabel();
        lblNuevoProducto.setText("<HTML><U>NUEVO PRODUCTO</U></HTML>");
        lblNuevoProducto.setBounds(150, 400, 100, 30);
        lblNuevoProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAP.add(lblNuevoProducto);

        lblNombreProducto = new JLabel();
        lblNombreProducto.setText("Nombre:");
        lblNombreProducto.setBounds(170, 440, 100, 30);
        panelAP.add(lblNombreProducto);

        lblPrecioProducto = new JLabel();
        lblPrecioProducto.setText("Precio:");
        lblPrecioProducto.setBounds(170, 470, 100, 30);
        panelAP.add(lblPrecioProducto);

        lblCantidadProducto = new JLabel();
        lblCantidadProducto.setText("Cantidad:");
        lblCantidadProducto.setBounds(170, 500, 100, 30);
        panelAP.add(lblCantidadProducto);

        lblImagenProducto = new JLabel();
        lblImagenProducto.setText("Imagen:");
        lblImagenProducto.setBounds(170, 530, 100, 30);
        panelAP.add(lblImagenProducto);

        //Campos para creacion de producto
        txtNombreProducto = new JTextField();
        txtNombreProducto.setBounds(235, 440, 125, 25);
        panelAP.add(txtNombreProducto);

        txtPrecioProducto = new JTextField();
        txtPrecioProducto.setBounds(235, 470, 125, 25);
        panelAP.add(txtPrecioProducto);

        txtCantidadProdcuto = new JTextField();
        txtCantidadProdcuto.setBounds(235, 500, 125, 25);
        panelAP.add(txtCantidadProdcuto);
        //Botones para creacion de productos
        btnImagenProducto = new JButton();
        btnImagenProducto.setText("Seleccionar...");
        btnImagenProducto.setBounds(235, 530, 125, 25);
        btnImagenProducto.addActionListener(this);
        panelAP.add(btnImagenProducto);

        btnAniadirProducto = new JButton();
        btnAniadirProducto.setText("Añadir producto");
        btnAniadirProducto.setBounds(170, 560, 190, 25);
        btnAniadirProducto.addActionListener(this);
        panelAP.add(btnAniadirProducto);
    }


    private JLabel lblModProdcuto, lblNombreProducto2, lblPrecioProducto2, lblCantidadProducto2, lblImagenProducto2;
    private JTextField txtNombreProducto2, txtPrecioProducto2, txtCantidadProdcuto2;
    private JButton btnImagenProducto2, btnModificarProducto;

    private void interfazModificacionProducto() {

        lblModProdcuto = new JLabel();
        lblModProdcuto.setText("<HTML><U>MODIFICACION DE UN PRODUCTO</U></HTML>");
        lblModProdcuto.setBounds(515, 400, 175, 30);
        lblModProdcuto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAP.add(lblModProdcuto);

        lblNombreProducto2 = new JLabel();
        lblNombreProducto2.setText("Nombre:");
        lblNombreProducto2.setBounds(535, 440, 100, 30);
        panelAP.add(lblNombreProducto2);

        lblPrecioProducto2 = new JLabel();
        lblPrecioProducto2.setText("Precio:");
        lblPrecioProducto2.setBounds(535, 470, 100, 30);
        panelAP.add(lblPrecioProducto2);

        lblCantidadProducto2 = new JLabel();
        lblCantidadProducto2.setText("Cantidad:");
        lblCantidadProducto2.setBounds(535, 500, 100, 30);
        panelAP.add(lblCantidadProducto2);

        lblImagenProducto2 = new JLabel();
        lblImagenProducto2.setText("Imagen:");
        lblImagenProducto2.setBounds(535, 530, 100, 30);
        panelAP.add(lblImagenProducto2);
        //Campos de texto para modificacion de productos
        txtNombreProducto2 = new JTextField();
        txtNombreProducto2.setBounds(600, 440, 125, 25);
        panelAP.add(txtNombreProducto2);

        txtPrecioProducto2 = new JTextField();
        txtPrecioProducto2.setBounds(600, 470, 125, 25);
        panelAP.add(txtPrecioProducto2);

        txtCantidadProdcuto2 = new JTextField();
        txtCantidadProdcuto2.setBounds(600, 500, 125, 25);
        panelAP.add(txtCantidadProdcuto2);
        //Botones para modificacion de productos
        btnImagenProducto2 = new JButton();
        btnImagenProducto2.setText("Seleccionar...");
        btnImagenProducto2.setBounds(600, 530, 125, 25);
        btnImagenProducto2.addActionListener(this);
        panelAP.add(btnImagenProducto2);

        btnModificarProducto = new JButton();
        btnModificarProducto.setText("Modificar cliente");
        btnModificarProducto.setBounds(535, 560, 190, 25);
        btnModificarProducto.addActionListener(this);
        panelAP.add(btnModificarProducto);

    }

    private JLabel lblVistaProducto, lblConsultaProducto, lblNombreDelProducto;
    private JTextField txtConsulProducto;
    private JButton btnBuscarProducto;

    private void interfazVistaProducto() {
        lblVistaProducto = new JLabel();
        lblVistaProducto.setText("<HTML><U>VISTA INFORMATIVA DE UN PRODUCTO</U></HTML>");
        lblVistaProducto.setBounds(150, 670, 300, 30);
        lblVistaProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAP.add(lblVistaProducto);

        lblConsultaProducto = new JLabel();
        lblConsultaProducto.setText("Consulta del producto:");
        lblConsultaProducto.setBounds(210, 700, 150, 30);
        panelAP.add(lblConsultaProducto);

        lblNombreDelProducto = new JLabel();
        lblNombreDelProducto.setText("Nombre:");
        lblNombreDelProducto.setBounds(155, 730, 50, 30);
        panelAP.add(lblNombreDelProducto);

        txtConsulProducto = new JTextField();
        txtConsulProducto.setBounds(210, 730, 150, 25);
        panelAP.add(txtConsulProducto);

        btnBuscarProducto = new JButton();
        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.setBounds(285, 760, 75, 25);
        btnBuscarProducto.addActionListener(this);
        panelAP.add(btnBuscarProducto);

    }

    private JLabel lblEliminarProducto, lblConsulProducto2, lblNombreDelProducto2;
    private JTextField txtConsulProducto2;
    private JButton btnBuscarProducto2;

    private void interfazEliminarProducto() {
        lblEliminarProducto = new JLabel();
        lblEliminarProducto.setText("<HTML><U>ELIMINACION DE UN PRODUCTO</U></HTML>");
        lblEliminarProducto.setBounds(525, 670, 300, 30);
        lblEliminarProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAP.add(lblEliminarProducto);

        lblConsulProducto2 = new JLabel();
        lblConsulProducto2.setText("Consulta del producto:");
        lblConsulProducto2.setBounds(580, 700, 150, 30);
        panelAP.add(lblConsulProducto2);

        lblNombreDelProducto2 = new JLabel();
        lblNombreDelProducto2.setText("Nombre:");
        lblNombreDelProducto2.setBounds(525, 730, 50, 30);
        panelAP.add(lblNombreDelProducto2);

        txtConsulProducto2 = new JTextField();
        txtConsulProducto2.setBounds(580, 730, 150, 25);
        panelAP.add(txtConsulProducto2);

        btnBuscarProducto2 = new JButton();
        btnBuscarProducto2.setText("Buscar");
        btnBuscarProducto2.setBounds(655, 760, 75, 25);
        btnBuscarProducto2.addActionListener(this);
        panelAP.add(btnBuscarProducto2);
    }

    private void btnImagenProductoAccion() {
        JFileChooser fc1 = new JFileChooser();
        fc1.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc1.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg");
        fc1.addChoosableFileFilter(filter);

        int op1 = fc1.showOpenDialog(this);
        if (op1 == JFileChooser.APPROVE_OPTION) {
            String file = fc1.getSelectedFile().toString();
            btnImagenProducto.setText(file);
        }
    }

    private void btnAniadirProductoAccion(){
        if ((txtNombreProducto.getText().length() == 0) || (txtPrecioProducto.getText().length() == 0) || (txtCantidadProdcuto.getText().length() == 0)  ){

            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");

        }else {
            JOptionPane.showMessageDialog(null, "El producto " + txtNombreProducto.getText() + " ha sido agregado al sistema.");
            txtNombreProducto.setText(null);
            txtCantidadProdcuto.setText(null);
            txtPrecioProducto.setText(null);
            btnImagenProducto.setText("Seleccionar...");
        }

    }

    private void btnBuscarProdcuctoAccion(){
        if (txtConsulProducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debes ingresar el nombre del producto para que se efectue la busqueda");
        } else {
            VistaInformativa VI = new VistaInformativa();
            VI.setVisible(true);
            txtConsulProducto.setText(null);
        }
    }

    private void btnEliminarProductoAccion(){
        if (txtConsulProducto2.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debes ingresar el nombre del producto para que se efectue la eliminacion");
        } else {
            JOptionPane.showMessageDialog(null, "El producto " + txtConsulProducto2.getText() + " ha sido eliminado correctamente");
        }
    }

}
