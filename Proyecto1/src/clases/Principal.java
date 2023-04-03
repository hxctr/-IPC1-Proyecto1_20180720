package clases;

import javax.swing.*;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
        } catch (InstantiationException e) {
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            //  e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // e.printStackTrace();
        }

        Login login = new Login();
        login.setVisible(true);
        //Al momento de terminar la ventana de Adminsitracion de clientes eliminar lo de abajo y descomentear lo de arriba
        /*Reportes rp = new Reportes();
        rp.setVisible(true);*/




    }



}
