package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    private JPanel panel;
    private JTextField campoUser;
    private JLabel etiquetaLogin, etiquetaUsuario, etiquetaContraseña;
    private JPasswordField campoPass;
    private JButton botonLogin;
    Registro a[] = new Registro[1];
    public static String usuario;
    int contador = 0;
    VentanaPrincipal VP;


    public Login() {
        setSize(800, 500);
        setTitle("Ventana de autenticación");
        setLocationRelativeTo(null);
        iniciarComponentesL();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void iniciarComponentesL() {
        colocarPanel();
        colocarEtiquetas();
        colocarCampos();
        colocarBotones();
        interfazDelLogin();
    }

    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
    }

    private JLabel lblNombre, lblApellido;
    private void colocarEtiquetas() {
        etiquetaLogin = new JLabel();
        etiquetaLogin.setBounds(585, 80, 400, 40);
        etiquetaLogin.setText("<HTML><U>REGISTRO</U></HTML>");
        etiquetaLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(etiquetaLogin);

        lblNombre = new JLabel();
        lblNombre.setBounds(520, 120, 400, 40);
        lblNombre.setText("Nombre:");
        panel.add(lblNombre);

        lblApellido = new JLabel();
        lblApellido.setBounds(520, 165, 400, 40);
        lblApellido.setText("Apellido:");
        panel.add(lblApellido);

        etiquetaUsuario = new JLabel();
        etiquetaUsuario.setBounds(520, 210, 400, 40);
        etiquetaUsuario.setText("Usuario:");
        panel.add(etiquetaUsuario);

        etiquetaContraseña = new JLabel();
        etiquetaContraseña.setBounds(520, 255, 400, 40);
        etiquetaContraseña.setText("Contraseña:");
        panel.add(etiquetaContraseña);

    }

    private JTextField txtNombre, txtApellido;
    private void colocarCampos() {
        txtNombre = new JTextField();
        txtNombre.setBounds(600, 120, 100, 30);
        panel.add(txtNombre);

        txtApellido = new JTextField();
        txtApellido.setBounds(600, 165, 100, 30);
        panel.add(txtApellido);


        campoUser = new JTextField();
        campoUser.setBounds(600, 210, 100, 30);
        panel.add(campoUser);


        /*campo1 = new JTextField();
        campo1.setBounds(185, 125, 100, 30);
        panel.add(campo1);*/

        campoPass = new JPasswordField();
        campoPass.setBounds(600, 255, 100, 30);
        panel.add(campoPass);

    }

    private void colocarBotones() {

        botonLogin = new JButton();
        botonLogin.setBounds(520, 300, 180, 30);
        botonLogin.setText("Registrar cuenta");
        botonLogin.addActionListener(this);
        panel.add(botonLogin);

    }

    private JLabel lblLogin, lblUsuario, lblContresenia;
    public static JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnIngresar;

    private void interfazDelLogin(){
        lblLogin = new JLabel();
        lblLogin.setBounds(180, 170, 150, 40);
        lblLogin.setText("<HTML><U>INGRESO</U></HTML>");
        lblLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(lblLogin);

        lblUsuario = new JLabel();
        lblUsuario.setBounds(120, 210, 400, 40);
        lblUsuario.setText("Usuario:");
        panel.add(lblUsuario);

        lblContresenia = new JLabel();
        lblContresenia.setBounds(120, 255, 400, 40);
        lblContresenia.setText("Contraseña:");
        panel.add(lblContresenia);
        //Campos
        txtUser = new JTextField();
        txtUser.setBounds(200, 210, 100, 30);
        panel.add(txtUser);

        txtPass = new JPasswordField();
        txtPass.setBounds(200, 255, 100, 30);
        panel.add(txtPass);
        //Boton
        btnIngresar = new JButton();
        btnIngresar.setBounds(120, 300, 180, 30);
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(this);
        panel.add(btnIngresar);

    }

    private void btnLoginAccion(){
        if ((txtNombre.getText().length() == 0) || (txtApellido.getText().length() == 0) || (campoUser.getText().length() == 0) || (campoPass.getPassword().length == 0)){
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
        }else{
            String contrasenia = "";


            if (contador != 10){
                for (int i = 0; i < a.length; i++) {
                    usuario = campoUser.getText();
                    char passArray[] = campoPass.getPassword();
                    contrasenia = new String(passArray);
                    a[i] = new Registro(usuario, contrasenia);

                }
                JOptionPane.showMessageDialog(null, "Bienvenido " + usuario);

                VP = new VentanaPrincipal();
                VP.setVisible(true);
                this.dispose();



                for (int i = 0; i < a.length; i++) {
                    System.out.println(a[i].getUsuario() + " " + a[i].getContrasenia());
                }
                contador++;



            }else{
                JOptionPane.showMessageDialog(null, "El sistema ya esta lleno");
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == botonLogin) {
            btnLoginAccion();
        }else if (actionEvent.getSource() == btnIngresar){
            btnIngresarAccion();
        }
    }

    private void btnIngresarAccion(){
        if ((txtUser.getText().length() == 0) || (txtPass.getPassword().length == 0)){
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
        }else{
            JOptionPane.showMessageDialog(null, "Bienvenido "+txtUser.getText());
            VentanaPrincipal VP = new VentanaPrincipal();
            VP.setVisible(true);
            this.dispose();
        }

    }
}
