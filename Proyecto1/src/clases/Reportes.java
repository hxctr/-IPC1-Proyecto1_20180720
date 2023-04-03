package clases;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Reportes extends JFrame implements ActionListener {

    private JPanel panelR;
    private JButton btnHTML, btnPDF;

    public Reportes() {
        setSize(500, 500);
        setTitle("Reportes");
        setLocationRelativeTo(null);
        iniciarComponentesR();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void iniciarComponentesR() {
        colocarPanelR();
        colocarBotones();
    }

    private void colocarPanelR() {
        panelR = new JPanel();
        panelR.setLayout(null);
        this.add(panelR);
    }

    private void colocarBotones() {
        btnHTML = new JButton();
        btnHTML.setBounds(67, 150, 350, 30);
        btnHTML.setText("Generar reporte en HTML");
        btnHTML.addActionListener(this);
        panelR.add(btnHTML);

        btnPDF = new JButton();
        btnPDF.setBounds(67, 230, 350, 30);
        btnPDF.setText("Generar reporte en PDF");
        btnPDF.addActionListener(this);
        panelR.add(btnPDF);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnHTML) {
            convertirVectorAMatriz();
        }else if (actionEvent.getSource() == btnPDF){
            reportePDF();
        }
    }

    private String[][] matrizDeClientes = new String[10][10];
    private String[][] matrizDeProductos = new String[10][10];
    private double[][] matrizDeVentas = new double[10][10];

    private void convertirVectorAMatriz() {
        int vectorPos = 0;
        int vectorPos2 = 0;
        int vectorPos3 = 0;


        //Convertir vector de clientes a matriz
        for (int i = 0; i < matrizDeClientes.length; i++) {

            for (int j = 0; j < matrizDeClientes[i].length; j++) {

                if (vectorPos < AdministracionDeClientes.vectorClientes.length)
                    matrizDeClientes[i][j] = AdministracionDeClientes.vectorClientes[vectorPos++ /*incrementar la posición*/];
            }
        }

        //Convertir vector de productos a matriz

        for (int i = 0; i < matrizDeProductos.length; i++) {

            for (int j = 0; j < matrizDeProductos[i].length; j++) {

                if (vectorPos2 < AdministracionDeProductos.vectorDeProductos.length)
                    matrizDeProductos[i][j] = AdministracionDeProductos.vectorDeProductos[vectorPos2++];
            }
        }

        //Convertir vector de ventas a matriz

        for (int i = 0; i < matrizDeVentas.length; i++) {

            for (int j = 0; j < matrizDeVentas[i].length; j++) {

                if (vectorPos3 < AdministracionDeVentas.matrizResultante.length)
                    matrizDeVentas[i][j] = AdministracionDeVentas.matrizResultante[vectorPos3++ /*incrementar la posición*/];
            }
        }


        reporteHTML(matrizDeClientes, matrizDeProductos, matrizDeVentas);

    }



    String username;

    private void reporteHTML(String[][] matrizDeClientes, String[][] matrizDeProductos, double[][] matrizDeVentas) {

        if (Login.txtUser.getText().length() == 0){
            username = Login.usuario;
        }else{
            username = Login.txtUser.getText();
        }

        Scanner sc = new Scanner(System.in);
        String rutaReporte = username+".html";

        try {

            FileWriter archivo = new FileWriter(rutaReporte);

            archivo.write("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<meta charset=\"utf-8\" />\n"
                    + "<title>Reporte en HTML</title>\n"
                    + "</head>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\">"
                    + "<body bgcolor=\"turquoise\">"
                    + " <h1 align=\"center\">Reporte en html</h1>\n"
                    + "<hr>\n"
                    + "<p><b>Este reporte ha sido generado por el usuario '<strong>" + username + "</strong>'</b></p>\n"
                    + "<hr>\n"
                    + "<h2 style=\"color:#1F2FE0\";>Listado de todos los clientes registrados</h2>\n");

            archivo.write("<table>\n");
            for (int i = 0; i < matrizDeClientes.length; i++) {
                archivo.write("<tr>\n");
                for (int j = 0; j < matrizDeClientes[i].length; j++) {
                    archivo.write("<th>" + matrizDeClientes[i][j] + "</th>\n");
                }
                archivo.write("</tr>\n");
            }
            archivo.write("</table>\n");


            //---------------------------------------------------

            archivo.write("<hr>\n"
                    + "<h2 style=\"color:#1F2FE0\";>Listado de todos los productos registrados</h2>\n");

            archivo.write("<table>\n");
            for (int i = 0; i < matrizDeProductos.length; i++) {
                archivo.write("<tr>\n");
                for (int j = 0; j < matrizDeProductos[i].length; j++) {
                    archivo.write("<th>" + matrizDeProductos[i][j] + "</th>\n");
                }
                archivo.write("</tr>\n");
            }
            archivo.write("</table>\n");


            //---------------------------------------------------

            archivo.write("<hr>\n"
                    + "<h2 style=\"color:#1F2FE0\";>Listado de todas las ventas registradas, según total de venta</h2>\n");


            archivo.write("<table>\n");
            for (int i = 0; i < matrizDeVentas.length; i++) {
                archivo.write("<tr>\n");
                for (int j = 0; j < matrizDeVentas[i].length; j++) {
                    archivo.write("<th>" + matrizDeVentas[i][j] + "</th>\n");
                }
                archivo.write("</tr>\n");
            }
            archivo.write("</table>\n");


            archivo.write("</body>\n"
                    + "</html>\n");
            archivo.close();
            System.out.println("*****Reporte creado con exito*****");
            JOptionPane.showMessageDialog(null, "El reporte en HTML se ha creado exitosamente");
            System.out.println("\n");

        } catch (IOException e) {
            System.out.println("Algo salio mal");
        }
    }

    private void reportePDF(){

        if (Login.txtUser.getText().length() == 0){
            username = Login.usuario;
        }else{
            username = Login.txtUser.getText();
        }

        String contenido0;
        String contenido = "";
        String contenido3 = "";

        try (PDDocument doc = new PDDocument()) {
            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

                cont.beginText();
                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.setLeading(14.5f);
                cont.newLineAtOffset(25, 700);

                contenido0 = "Este reporte ha sido generado por "+ username;
                cont.showText(contenido0);
                cont.newLine();

                contenido0 = "Listado de los 10 productos más vendidos";
                cont.showText(contenido0);
                cont.newLine();

                for (int i = 0; i < 10; i++) {
                    contenido = AdministracionDeProductos.vectorDeProductos[i] + "";
                    cont.showText(contenido);
                    cont.newLine();
                }

                contenido0 = "Listado de las 10 ventas con mayor total.";
                cont.showText(contenido0);
                cont.newLine();

                for (int i = 0; i < 10; i++) {
                    contenido = AdministracionDeVentas.matrizResultante[i] + "";
                    cont.showText(contenido);
                    cont.newLine();
                }

                cont.endText();


            }
            doc.save(username+".pdf");
            doc.close();
            JOptionPane.showMessageDialog(null, "El reporte en PDF se ha creado exitosamente");
        } catch (Exception ex) {
            System.out.println(ex);
        }










    }

}
