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


public class AdministracionDeClientes extends JFrame implements ActionListener {

    private JPanel panelAC;
    private JButton btnCargaMasivaAC, btnDashboardAC, btnGraficaDePie, btnGraficaDeBarras;
    private JRadioButton rbtnCreacionCliente, rbtnVistacliente, rbtnModificarCliente, rbtnEliminarCliente;
    private JLabel lblNuevoCliente, lblNombre, lblEdad, lblSexo, lblNIT, lblAvatar, lblModCliente;
    private JLabel lblNombre2, lblEdad2, lblSexo2, lblNIT2, lblAvatar2;
    private JButton btnAvatar, btnAvatar2;
    private JTextField txtfldNombre, txtfldEdad, txtNIT, txtfldNombre2, txtflEdad2, txtNIT2;
    private JComboBox cmbSexo, cmbSexo2;
    private JButton btnAniadirCliente, btnModCliente;

    //Etiquetas rbtVistaCliente
    private JLabel lblVistaCliente, lblConsultaCliente, lblNIT3;
    private JTextField txtConsulCliente;
    private JButton btnBuscar;
    //Etiquetas rbtEliminarcliente
    private JLabel lblEliminarcliente, lblConsultaCliente2, lblNIT4;
    private JTextField txtConsulCliente2;
    private JButton btnBuscar2;

    CargaMasivaClientes[] datosClientes = new CargaMasivaClientes[300];
    private Object[][] columnas;

    public static int contadorClientes = 0, contadorHombre = 0, contadorMujeres = 0, contador0a20 = 0,
                      contadodr21a40 = 0, contador41a60 = 0, contador61a80 = 0, contaodor81a100 = 0;


    public AdministracionDeClientes() {
        setSize(900, 1000);
        setTitle("Administracion de clientes");
        setLocationRelativeTo(null);
        iniciarComponentesAC();
        setResizable(true);
        cerar();
    }

    public void cerar() {
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

    public void iniciarComponentesAC() {
        colocarPanelAC();
        colocarBotonesAC();
        rbtCreacionClienteInterfaz();
        rbtnModCliente();
        rbtVistaCliente();
        rbtEliminarCliente();
    }

    private void colocarPanelAC() {
        panelAC = new JPanel();
        panelAC.setLayout(null);
        this.add(panelAC);
    }

    public void colocarBotonesAC() {
        btnCargaMasivaAC = new JButton();
        btnCargaMasivaAC.setBounds(48, 60, 800, 30);
        btnCargaMasivaAC.setText("Carga masiva de clientes");
        btnCargaMasivaAC.addActionListener(this);
        panelAC.add(btnCargaMasivaAC);

        btnDashboardAC = new JButton();
        btnDashboardAC.setBounds(48, 100, 800, 30);
        btnDashboardAC.setText("Dashboard de clientes en el sistema");
        btnDashboardAC.addActionListener(this);
        panelAC.add(btnDashboardAC);

        btnGraficaDePie = new JButton();
        btnGraficaDePie.setBounds(225, 325, 200, 30);
        btnGraficaDePie.setText("Ver grafica de pie");
        btnGraficaDePie.addActionListener(this);
        panelAC.add(btnGraficaDePie);

        btnGraficaDeBarras = new JButton();
        btnGraficaDeBarras.setBounds(460, 325, 200, 30);
        btnGraficaDeBarras.setText("Ver grafica de barras");
        btnGraficaDeBarras.addActionListener(this);
        panelAC.add(btnGraficaDeBarras);

        /*btnCreacionCliente = new JButton();
        btnCreacionCliente.setBounds(65, 380, 150, 50);
        btnCreacionCliente.setText("Creacion de clientes");
        btnCreacionCliente.addActionListener(this);
        panelAC.add(btnCreacionCliente);*/


    }

    public void colocarRadioBotones() {
        rbtnCreacionCliente = new JRadioButton("Creacion de clientes", true);
        rbtnCreacionCliente.setBounds(65, 380, 150, 50);
        panelAC.add(rbtnCreacionCliente);

        rbtnVistacliente = new JRadioButton("Vista informativa de un cliente", true);
        rbtnVistacliente.setBounds(235, 380, 200, 50);
        panelAC.add(rbtnVistacliente);

        rbtnModificarCliente = new JRadioButton("Modificacion de un cliente", true);
        rbtnModificarCliente.setBounds(460, 380, 200, 50);
        panelAC.add(rbtnModificarCliente);

        rbtnEliminarCliente = new JRadioButton("Modificacion de un cliente", true);
        rbtnEliminarCliente.setBounds(660, 380, 200, 50);
        panelAC.add(rbtnEliminarCliente);

        ButtonGroup radioBotones = new ButtonGroup();
        radioBotones.add(rbtnCreacionCliente);
        radioBotones.add(rbtnVistacliente);
        radioBotones.add(rbtnModificarCliente);
        radioBotones.add(rbtnEliminarCliente);


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCargaMasivaAC) {
            cargaMasivaDeClientes();
        } else if (actionEvent.getSource() == btnDashboardAC) {
            tablaCargaMasiva();
        } else if (actionEvent.getSource() == btnGraficaDePie) {
            graficaHyM();
        } else if (actionEvent.getSource() == btnGraficaDeBarras) {
            graficaCxE();
        } else if (actionEvent.getSource() == btnAvatar) {
            btnAvatarAccion();
        } else if (actionEvent.getSource() == btnAniadirCliente) {
            btnAniadirClienteAccion();
        } else if (actionEvent.getSource() == btnBuscar) {
            btnBuscarAccion();
        } else if (actionEvent.getSource() == btnBuscar2) {
            btnBuscar2Accion();
        }else if (actionEvent.getSource() == btnAvatar2){
            btnAvatas2Accion();
        }
    }


    private int i = 0;
    private String[] spliteado;
    public static String[] vectorClientes = new String[300];

    public void cargaMasivaDeClientes() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos .csv", "csv");
        fileChooser.addChoosableFileFilter(filtro);

        int operacion = fileChooser.showOpenDialog(this);
        if (operacion == JFileChooser.APPROVE_OPTION) {//Comienza el if
            try {

                String locacion = fileChooser.getSelectedFile().toString();

                File fichero = fileChooser.getSelectedFile();
                FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr);

                String contenido;
                while ((contenido = br.readLine()) != null) {
                    spliteado = contenido.split(",");
                    String nombre = spliteado[0];
                    int edad = Integer.parseInt(spliteado[1]);

                    String sexo = spliteado[2];
                    int NIT = Integer.parseInt(spliteado[3]);
                    String avatar = spliteado[4];

                    if (spliteado[2].equals("M")) {
                        contadorHombre++;
                    }
                    if (spliteado[2].equals("F")) {
                        contadorMujeres++;
                    }


                    vectorClientes[i] = nombre;
                    i++;

                    if (edad >= 1 && edad <= 20) {
                        contador0a20++;
                    } else if (edad >= 21 && edad <= 40) {
                        contadodr21a40++;
                    } else if (edad >= 41 && edad <= 60) {
                        contador41a60++;
                    } else if (edad >= 61 && edad <= 80) {
                        contador61a80++;
                    } else if (edad >= 81 && edad <= 100) {
                        contaodor81a100++;
                    }


                    datosClientes[contadorClientes] = new CargaMasivaClientes(nombre, edad, sexo, NIT, avatar);
                    contadorClientes++;
                }
                JOptionPane.showMessageDialog(null, "El archivo .csv fue cargado correctamente");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void tablaCargaMasiva() {
        columnas = new Object[contadorClientes][4];

        for (int i = 0; i < contadorClientes; i++) {
            columnas[i][0] = datosClientes[i].getNombre();
            columnas[i][1] = datosClientes[i].getEdad();
            columnas[i][2] = datosClientes[i].getSexo();
            columnas[i][3] = datosClientes[i].getNIT();
        }

        String[] encabezados = {"Nombre", "Edad", "Sexo", "NIT"};
        JTable tabla = new JTable(columnas, encabezados);
        tabla.setBounds(300, 200, 300, 100);
        panelAC.add(tabla);

        JScrollPane scroll = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(300, 200, 300, 100);
        panelAC.add(scroll);
    }

    public void graficaHyM() {
        GraficaClientesPorSexo example = new GraficaClientesPorSexo("Gráfica de pie de clientes por sexo");
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        example.setVisible(true);

    }

    public void graficaCxE() {
        GraficaClientesPorEdad example = new GraficaClientesPorEdad("Gráfica de barras de cantidad de clientes por rango de edad");
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        example.setVisible(true);
    }

    public void rbtnModCliente() {

        //Etiquetas para vista cliente
        lblModCliente = new JLabel();
        lblModCliente.setText("<HTML><U>MODIFICACION DE UN CLIENTE</U></HTML>");
        lblModCliente.setBounds(515, 400, 175, 30);
        lblModCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAC.add(lblModCliente);

        lblNombre2 = new JLabel();
        lblNombre2.setText("Nombre:");
        lblNombre2.setBounds(535, 440, 100, 30);
        panelAC.add(lblNombre2);

        lblEdad2 = new JLabel();
        lblEdad2.setText("Edad:");
        lblEdad2.setBounds(535, 470, 100, 30);
        panelAC.add(lblEdad2);

        lblSexo2 = new JLabel();
        lblSexo2.setText("Sexo:");
        lblSexo2.setBounds(535, 500, 100, 30);
        panelAC.add(lblSexo2);

        lblNIT2 = new JLabel();
        lblNIT2.setText("NIT:");
        lblNIT2.setBounds(535, 530, 100, 30);
        panelAC.add(lblNIT2);

        lblAvatar2 = new JLabel();
        lblAvatar2.setText("Avatar:");
        lblAvatar2.setBounds(535, 560, 100, 30);
        panelAC.add(lblAvatar2);


        //Campos de texto para vista cliente
        txtfldNombre2 = new JTextField();
        txtfldNombre2.setBounds(600, 440, 125, 25);
        panelAC.add(txtfldNombre2);

        txtflEdad2 = new JTextField();
        txtflEdad2.setBounds(600, 470, 125, 25);
        panelAC.add(txtflEdad2);

        String[] sexos2 = {"M", "F", ""};
        cmbSexo2 = new JComboBox(sexos2);
        cmbSexo2.setBounds(600, 500, 100, 25);
        cmbSexo2.setSelectedItem("");
        panelAC.add(cmbSexo2);

        txtNIT2 = new JTextField();
        txtNIT2.setBounds(600, 530, 125, 25);
        panelAC.add(txtNIT2);
        //Botones
        btnAvatar2 = new JButton();
        btnAvatar2.setText("Seleccionar...");
        btnAvatar2.setBounds(600, 560, 125, 25);
        btnAvatar2.addActionListener(this);
        panelAC.add(btnAvatar2);

        btnModCliente = new JButton();
        btnModCliente.setText("Modificar cliente");
        btnModCliente.setBounds(535, 590, 190, 25);
        btnModCliente.addActionListener(this);
        panelAC.add(btnModCliente);
    }


    public void rbtCreacionClienteInterfaz() {
        lblNuevoCliente = new JLabel();
        lblNuevoCliente.setText("<HTML><U>NUEVO CLIENTE</U></HTML>");
        lblNuevoCliente.setBounds(150, 400, 100, 30);
        lblNuevoCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAC.add(lblNuevoCliente);

        lblNombre = new JLabel();
        lblNombre.setText("Nombre:");
        lblNombre.setBounds(170, 440, 100, 30);
        panelAC.add(lblNombre);

        lblEdad = new JLabel();
        lblEdad.setText("Edad:");
        lblEdad.setBounds(170, 470, 100, 30);
        panelAC.add(lblEdad);

        lblSexo = new JLabel();
        lblSexo.setText("Sexo:");
        lblSexo.setBounds(170, 500, 100, 30);
        panelAC.add(lblSexo);

        lblNIT = new JLabel();
        lblNIT.setText("NIT:");
        lblNIT.setBounds(170, 530, 100, 30);
        panelAC.add(lblNIT);

        lblAvatar = new JLabel();
        lblAvatar.setText("Avatar:");
        lblAvatar.setBounds(170, 560, 100, 30);
        panelAC.add(lblAvatar);

//Campos-----------------------------------------------
        txtfldNombre = new JTextField();
        txtfldNombre.setBounds(235, 440, 125, 25);
        panelAC.add(txtfldNombre);

        txtfldEdad = new JTextField();
        txtfldEdad.setBounds(235, 470, 125, 25);
        panelAC.add(txtfldEdad);

        String[] sexos = {"M", "F", ""};
        cmbSexo = new JComboBox(sexos);
        cmbSexo.setBounds(235, 500, 100, 25);
        cmbSexo.setSelectedItem("");
        panelAC.add(cmbSexo);

        txtNIT = new JTextField();
        txtNIT.setBounds(235, 530, 125, 25);
        panelAC.add(txtNIT);
//Boton
        btnAvatar = new JButton();
        btnAvatar.setText("Seleccionar...");
        btnAvatar.setBounds(235, 560, 125, 25);
        btnAvatar.addActionListener(this);
        panelAC.add(btnAvatar);

        btnAniadirCliente = new JButton();
        btnAniadirCliente.setText("Añadir cliente");
        btnAniadirCliente.setBounds(170, 590, 190, 25);
        btnAniadirCliente.addActionListener(this);
        panelAC.add(btnAniadirCliente);

    }

    public void rbtVistaCliente() {
        lblVistaCliente = new JLabel();
        lblVistaCliente.setText("<HTML><U>VISTA INFORMATIVA DE UN CLIENTE</U></HTML>");
        lblVistaCliente.setBounds(150, 670, 300, 30);
        lblVistaCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAC.add(lblVistaCliente);

        lblConsultaCliente = new JLabel();
        lblConsultaCliente.setText("Consulta de cliente:");
        lblConsultaCliente.setBounds(210, 700, 150, 30);
        panelAC.add(lblConsultaCliente);

        lblNIT3 = new JLabel();
        lblNIT3.setText("NIT:");
        lblNIT3.setBounds(175, 730, 50, 30);
        panelAC.add(lblNIT3);

        txtConsulCliente = new JTextField();
        txtConsulCliente.setBounds(210, 730, 150, 25);
        panelAC.add(txtConsulCliente);

        btnBuscar = new JButton();
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(285, 760, 75, 25);
        btnBuscar.addActionListener(this);
        panelAC.add(btnBuscar);
    }

    public void rbtEliminarCliente() {
        lblEliminarcliente = new JLabel();
        lblEliminarcliente.setText("<HTML><U>ELIMINACION DE UN CLIENTE</U></HTML>");
        lblEliminarcliente.setBounds(550, 670, 300, 30);
        lblEliminarcliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAC.add(lblEliminarcliente);

        lblConsultaCliente2 = new JLabel();
        lblConsultaCliente2.setText("Consulta de cliente:");
        lblConsultaCliente2.setBounds(580, 700, 150, 30);
        panelAC.add(lblConsultaCliente2);

        lblNIT4 = new JLabel();
        lblNIT4.setText("NIT:");
        lblNIT4.setBounds(545, 730, 50, 30);
        panelAC.add(lblNIT4);

        txtConsulCliente2 = new JTextField();
        txtConsulCliente2.setBounds(580, 730, 150, 25);
        panelAC.add(txtConsulCliente2);

        btnBuscar2 = new JButton();
        btnBuscar2.setText("Buscar");
        btnBuscar2.setBounds(655, 760, 75, 25);
        btnBuscar2.addActionListener(this);
        panelAC.add(btnBuscar2);
    }

    private void btnAvatarAccion() {
        JFileChooser fc1 = new JFileChooser();
        fc1.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc1.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg");
        fc1.addChoosableFileFilter(filter);

        int op1 = fc1.showOpenDialog(this);
        if (op1 == JFileChooser.APPROVE_OPTION) {
            String file = fc1.getSelectedFile().toString();
            btnAvatar.setText(file);
        }
    }

    public void btnAvatas2Accion(){
        JFileChooser fc2 = new JFileChooser();
        fc2.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc2.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg");
        fc2.addChoosableFileFilter(filter);

        int op2 = fc2.showOpenDialog(this);
        if (op2 == JFileChooser.APPROVE_OPTION) {

            String file = fc2.getSelectedFile().toString();
            btnAvatar2.setText(file);
        }
    }


    public void btnAniadirClienteAccion() {

        if ((txtfldNombre.getText().length() == 0) || (txtfldEdad.getText().length() == 0) || (txtNIT.getText().length() == 0) || (cmbSexo.getSelectedItem().equals(""))){
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
        }else {
            if (cmbSexo.getSelectedItem().equals("M")) {
                JOptionPane.showMessageDialog(null, "El cliente " + txtfldNombre.getText() + " ha sido agregado al sistema.");
                txtfldNombre.setText(null);
                txtfldEdad.setText(null);
                txtNIT.setText(null);
                cmbSexo.setSelectedItem("");
                btnAvatar.setText("Seleccionar...");
            } else {
                JOptionPane.showMessageDialog(null, "La cliente " + txtfldNombre.getText() + " ha sido agregada al sistema.");
                txtfldNombre.setText(null);
                txtfldEdad.setText(null);
                txtNIT.setText(null);
                cmbSexo.setSelectedItem("");
                btnAvatar.setText("Seleccionar...");
            }
        }
    }

    public void btnBuscarAccion() {
        if (txtConsulCliente.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debes ingresar el NIT para que se efectue la busqueda");
        } else {
            VistaInformativa VI = new VistaInformativa();
            VI.setVisible(true);
            txtConsulCliente.setText(null);
        }
    }

    public void btnBuscar2Accion() {
        if (txtConsulCliente2.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debes ingresar el NIT para que se efectue la eliminacion");
        } else {
            JOptionPane.showMessageDialog(null, "El cliente con NIT " + txtConsulCliente2.getText() + " ha sido eliminado correctamente");
        }
    }



    /*public void eventosDeAccionRadioBotones() {

        ActionListener eventoAccion1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rbtCreacionClienteInterfaz();

                JOptionPane.showMessageDialog(null, "Evento con creacion aparte");
            }
        };
        rbtnCreacionCliente.addActionListener(eventoAccion1);

        ActionListener eventoAccion2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rbtnVistaCliente();


                JOptionPane.showMessageDialog(null, "Segundo rbtn");
            }
        };
        rbtnVistacliente.addActionListener(eventoAccion2);


    }*/


}
