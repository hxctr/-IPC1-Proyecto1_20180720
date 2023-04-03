package clases;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.text.DecimalFormat;

public class GraficaClientesPorSexo extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    public GraficaClientesPorSexo(String title) {
        super(title);

        // Create dataset
        PieDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Grafica de pie",
                dataset,
                true,
                true,
                false);

        //Format Label
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "Marks {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

    }

    private PieDataset createDataset() {

        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue( "Mujeres" , new Double( AdministracionDeClientes.contadorMujeres ) );
        dataset.setValue( "Hombres" , new Double( AdministracionDeClientes.contadorHombre) );
        return dataset;
    }
}
