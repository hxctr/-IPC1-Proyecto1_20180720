package clases;

import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import javafx.embed.swing.JFXPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class GraficaProductosPorPrecioBarras extends JFrame {

    private static final long serialVersionUID = 6294689542092367723L;

    public GraficaProductosPorPrecioBarras(String title) {
        super(title);

        // Create dataset
        PieDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Cantidad de productos por rango de precio",
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

        DefaultPieDataset dataset=new DefaultPieDataset();
        dataset.setValue("0-50", AdministracionDeProductos.contador0a50);
        dataset.setValue("51-100", AdministracionDeProductos.contador51a100);
        dataset.setValue("101-150", AdministracionDeProductos.contador101a150);
        dataset.setValue("151-200", AdministracionDeProductos.contador151a200);
        dataset.setValue("201-1000", AdministracionDeProductos.contador201a1000);
        return dataset;
    }






}
