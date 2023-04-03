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
import java.text.DecimalFormat;

public class AdministracionDeVentas extends JFrame implements ActionListener {

    private JPanel panelAV;
    private JButton btnCargaMasivaAV, btnDashboardAV;
    CargaMasivaVentas[] datosVentas = new CargaMasivaVentas[300];
    private int contadorVentas = 0;

    private Object[][] columnas;

    public AdministracionDeVentas() {
        setSize(900, 1000);
        setTitle("Administracion de ventas");
        setLocationRelativeTo(null);
        iniciarComponentesAV();
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

    public void iniciarComponentesAV() {
        colocarPanelAV();
        colocarBotones();
        etiquetasDelDashboard();
        interfazCreacionDeVenta();
        interfazVistaVenta();

    }

    private void colocarPanelAV() {
        panelAV = new JPanel();
        panelAV.setLayout(null);
        this.add(panelAV);
    }

    private void colocarBotones() {
        btnCargaMasivaAV = new JButton();
        btnCargaMasivaAV.setBounds(48, 60, 800, 30);
        btnCargaMasivaAV.setText("Carga masiva de ventas");
        btnCargaMasivaAV.addActionListener(this);
        panelAV.add(btnCargaMasivaAV);

        btnDashboardAV = new JButton();
        btnDashboardAV.setBounds(48, 100, 800, 30);
        btnDashboardAV.setText("Dashboard de ventas en el sistema");
        btnDashboardAV.addActionListener(this);
        panelAV.add(btnDashboardAV);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCargaMasivaAV) {
            btnCargaMasivaAVAccion();
        } else if (actionEvent.getSource() == btnDashboardAV) {
            btnDashboardAVAccion();
        }
    }

    private int mayor;
    double mayor2;
    private int k = 0;
    private int[] matriz = new int[300];//---
    private String[] spliteado;
    private int i = 0;
    public static Double[] matrizResultante = new Double[100];//matriz que servira para hacer la multiplicacion
    DecimalFormat df = new DecimalFormat("#.00");
    double total = 0;
    double IVATotal;
    int indiceDelMayor = 0;
    private String[] matrizDelNombreDelProducto = new String[300];//--
    private void btnCargaMasivaAVAccion() {
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
                    String codigoVenta = spliteado[0];
                    int NITDelCliente = Integer.parseInt(spliteado[1]);
                    String nombreDelProducto = spliteado[2];
                    int cantidadComprada = Integer.parseInt(spliteado[3]);

                    matriz[i] = cantidadComprada;//matriz de cantidad comprada
                    i++;
                    matrizDelNombreDelProducto[k] = nombreDelProducto;
                    k++;


                    datosVentas[contadorVentas] = new CargaMasivaVentas(codigoVenta, NITDelCliente, nombreDelProducto, cantidadComprada);
                    contadorVentas++;
                }

                JOptionPane.showMessageDialog(null, "El archivo .csv fue cargado correctamente");
                //Esta parte de codigo me hace la busqueda del numero mas grande en la columna de cantidades del arreglo
                mayor = matriz[0];

                for (int x = 1; x < matriz.length; x++) {
                    if (matriz[x] > mayor) {
                        mayor = matriz[x];
                    }
                }
                for (int x = 1; x < matriz.length; x++) {
                    if (matriz[x] > matriz[indiceDelMayor]) {
                        indiceDelMayor = x;
                    }
                }
                System.out.println("El mayor es: " + matriz[indiceDelMayor]);
                System.out.println("El indice del mayor es: "+indiceDelMayor);

                for (int j = 0; j < 100; j++) {
                    System.out.println(matriz[j]);
                }
                System.out.println("Aqui voy a probar a ver si si se me guarda el arreglo del precio de productos");
                for (int j = 0; j < AdministracionDeProductos.matrizDePrecios.length; j++){
                    System.out.println(AdministracionDeProductos.matrizDePrecios[j]);
                }
                //Aqui finaliza el codigo que me hacia la parte explicada arriba
                //Aqui voy a empezar con la multiplicacion de las matrices
                for (int i= 0; i < matrizResultante.length; i++ ){
                    matrizResultante[i] = (matriz[i]) * (AdministracionDeProductos.matrizDePrecios[i]);
                }
                //finalizo aqui
                System.out.println("Aqui muestro la multiplicacion de la matriz");

                for (int i = 0; i < matrizResultante.length; i++){
                    System.out.println(df.format(matrizResultante[i]));
                }
                System.out.println("Aqui muestro la cantidad mas alta de la matriz resultante");
                mayor2 = matrizResultante[0];


                for (int x = 1; x < matrizResultante.length; x++) {
                    if (matrizResultante[x] > mayor2) {
                        mayor2 = matrizResultante[x];
                    }
                }
                System.out.println("El mayor en la matriz resultante es: " + mayor2);








                System.out.println("aqui creo que queria mostrar la suma total");

                for (int contador = 0; contador < matrizResultante.length; contador++) {
                    total += matrizResultante[contador];
                }
                System.out.println(df.format(total));
                IVATotal = total*0.12;
                System.out.println(IVATotal);
                System.out.println("el nombre del producto mayor");

                for (int i = 0; i < matrizDelNombreDelProducto.length; i++){
                    System.out.println(matrizDelNombreDelProducto[i] +",");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void btnDashboardAVAccion() {
        columnas = new Object[contadorVentas][4];

        for (int i = 0; i < contadorVentas; i++) {
            columnas[i][0] = datosVentas[i].getCodigoVenta();
            columnas[i][1] = datosVentas[i].getNITDelCliente();
            columnas[i][2] = datosVentas[i].getNombreDelProducto();
            columnas[i][3] = datosVentas[i].getCantidadComprada();
        }

        String[] encabezados = {"Codigo de venta", "NIT del cliente", "Nombre del producto", "Cantidad comprada"};
        JTable tabla = new JTable(columnas, encabezados);
        tabla.setBounds(100, 200, 700, 100);
        panelAV.add(tabla);

        JScrollPane scroll = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(100, 200, 700, 100);
        panelAV.add(scroll);

        txtValorTotal.setText(df.format(total));
        txtTotalIVA.setText(df.format(IVATotal));
        lblVentaMasGrandeSegunCantidadProductos.setText("Venta más grande según cantidad de productos fue de "+matrizDelNombreDelProducto[indiceDelMayor]+" con una cantidad comprada de "+matriz[indiceDelMayor]);
        lblVentaMasGrandeSegunTotalDeVenta.setText("Venta más grande según el total de la venta fue de "+mayor2);




    }

    private JLabel lblValorTotalVentas, lblTotalIVAEnVentas, lblVentaMasGrandeSegunCantidadProductos, lblVentaMasGrandeSegunTotalDeVenta;
    private JTextField txtValorTotal, txtTotalIVA, txtVentaSegunCantProduct, txtVentaSegunTotalVenta;
    private void etiquetasDelDashboard(){

        lblValorTotalVentas = new JLabel();
        lblValorTotalVentas.setText("Valor total registrado en las ventas:");
        lblValorTotalVentas.setBounds(300, 315, 225, 30);
        panelAV.add(lblValorTotalVentas);

        lblTotalIVAEnVentas = new JLabel();
        lblTotalIVAEnVentas.setText("Total de IVA registrado en ventas:");
        lblTotalIVAEnVentas.setBounds(300,345, 225, 30);
        panelAV.add(lblTotalIVAEnVentas);

        lblVentaMasGrandeSegunCantidadProductos = new JLabel();
        lblVentaMasGrandeSegunCantidadProductos.setText("Venta más grande según cantidad de productos fue de");
        lblVentaMasGrandeSegunCantidadProductos.setBounds(300, 375, 750, 30);
        panelAV.add(lblVentaMasGrandeSegunCantidadProductos);

        lblVentaMasGrandeSegunTotalDeVenta = new JLabel();
        lblVentaMasGrandeSegunTotalDeVenta.setText("Venta más grande según el total de la venta fue de");
        lblVentaMasGrandeSegunTotalDeVenta.setBounds(300, 405, 750, 30);
        panelAV.add(lblVentaMasGrandeSegunTotalDeVenta);

        //Campos
        txtValorTotal = new JTextField();
        txtValorTotal.setEnabled(false);
        txtValorTotal.setBounds(600, 315, 125, 25);
        panelAV.add(txtValorTotal);

        txtTotalIVA = new JTextField();
        txtTotalIVA.setEnabled(false);
        txtTotalIVA.setBounds(600, 345, 125, 25);
        panelAV.add(txtTotalIVA);

        /*txtVentaSegunCantProduct = new JTextField();
        txtVentaSegunCantProduct.setEnabled(false);
        txtVentaSegunCantProduct.setBounds(600, 500, 125, 25);
        panelAV.add(txtVentaSegunCantProduct);*/

        /*txtVentaSegunTotalVenta = new JTextField();
        txtVentaSegunTotalVenta.setEnabled(false);
        txtVentaSegunTotalVenta.setBounds(600, 530, 125, 25);
        panelAV.add(txtVentaSegunTotalVenta);*/

    }

    private JLabel lblNuevaVenta, lblNITdelCliente, lblAgregarProducto, lblProducto, lblCantidad;
    private JTextField txtNitCliente, txtCantidad;
    private JComboBox cmbProducto;
    private JButton btnAgregar;

    public void interfazCreacionDeVenta(){
        lblNuevaVenta = new JLabel();
        lblNuevaVenta.setText("<HTML><U>NUEVA VENTA</U></HTML>");
        lblNuevaVenta.setBounds(150, 450, 100, 30);
        lblNuevaVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAV.add(lblNuevaVenta);

        lblNITdelCliente = new JLabel();
        lblNITdelCliente.setText("NIT:");
        lblNITdelCliente.setBounds(170, 490, 100, 30);
        panelAV.add(lblNITdelCliente);

        lblAgregarProducto = new JLabel();
        lblAgregarProducto.setText("Agregar producto");
        lblAgregarProducto.setBounds(170, 520, 100, 30);
        panelAV.add(lblAgregarProducto);

        lblProducto = new JLabel();
        lblProducto.setText("Producto:");
        lblProducto.setBounds(170, 550, 100, 30);
        panelAV.add(lblProducto);

        lblCantidad = new JLabel();
        lblCantidad.setText("Cantidad:");
        lblCantidad.setBounds(170, 580, 100, 30);
        panelAV.add(lblCantidad);
        //Campos de texto
        txtNitCliente = new JTextField();
        txtNitCliente.setBounds(235, 490, 125, 25);
        panelAV.add(txtNitCliente);

        cmbProducto = new JComboBox(matrizDelNombreDelProducto);
        cmbProducto.setBounds(235, 550, 125, 25);
        panelAV.add(cmbProducto);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(235, 580, 125, 25);
        panelAV.add(txtCantidad);

        btnAgregar = new JButton();
        btnAgregar.setText("Agregar");
        btnAgregar.setBounds(170, 610, 190, 25);
        btnAgregar.addActionListener(this);
        panelAV.add(btnAgregar);

    }

    private JLabel lblVistaVenta, lblConsultaVenta, lblCodigoVenta;
    private JTextField txtConsulVenta;
    private JButton btnBuscarCodigo;

    private void interfazVistaVenta(){
        lblVistaVenta = new JLabel();
        lblVistaVenta.setText("<HTML><U>VISTA INFORMATIVA DE UNA VENTA</U></HTML>");
        lblVistaVenta.setBounds(150, 670, 300, 30);
        lblVistaVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAV.add(lblVistaVenta);

        lblConsultaVenta = new JLabel();
        lblConsultaVenta.setText("Consulta de la venta:");
        lblConsultaVenta.setBounds(210, 700, 150, 30);
        panelAV.add(lblConsultaVenta);

        lblCodigoVenta = new JLabel();
        lblCodigoVenta.setText("Codigo:");
        lblCodigoVenta.setBounds(155, 730, 50, 30);
        panelAV.add(lblCodigoVenta);

        txtConsulVenta = new JTextField();
        txtConsulVenta.setBounds(210, 730, 150, 25);
        panelAV.add(txtConsulVenta);

        btnBuscarCodigo = new JButton();
        btnBuscarCodigo.setText("Buscar");
        btnBuscarCodigo.setBounds(285, 760, 75, 25);
        btnBuscarCodigo.addActionListener(this);
        panelAV.add(btnBuscarCodigo);
    }

    private void reporteHTML(){

    }


}
