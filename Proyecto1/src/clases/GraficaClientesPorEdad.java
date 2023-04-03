package clases;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraficaClientesPorEdad extends JFrame {

    private static final long serialVersionUID = 1L;

    public GraficaClientesPorEdad(String appTitle) {
        super(appTitle);

        // Create Dataset
        CategoryDataset dataset = createDataset();

        //Create chart
        JFreeChart chart=ChartFactory.createBarChart(
                "Cantidad de clientes por rango de edad", //Chart Title
                "Edades", // Category axis
                "Frecuencia Absoluta", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        ChartPanel panel=new ChartPanel(chart);
        setContentPane(panel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(AdministracionDeClientes.contador0a20, "1-20", "Rango");
        dataset.addValue(AdministracionDeClientes.contadodr21a40, "21-40", "Rango");
        dataset.addValue(AdministracionDeClientes.contador41a60, "41-60", "Rango");
        dataset.addValue(AdministracionDeClientes.contador61a80, "61-80", "Rango");
        dataset.addValue(AdministracionDeClientes.contaodor81a100, "91-100", "Rango");
        return dataset;
    }







}
