package clases;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficaProductosPorPrecio extends JFrame{
    //Gráfica de barras de la cantidad de productos por rango de precio.
    private static final long serialVersionUID = 1L;

    public GraficaProductosPorPrecio(String appTitle) {
        super(appTitle);

        // Create Dataset
        CategoryDataset dataset = createDataset();

        //Create chart
        JFreeChart chart=ChartFactory.createBarChart(
                "Cantidad de productos por rango de precios", //Chart Title
                "Precios", // Category axis
                "Cantidad de productos", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        ChartPanel panel=new ChartPanel(chart);
        setContentPane(panel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(AdministracionDeProductos.contador0a50, "0-50", "Rango");
        dataset.addValue(AdministracionDeProductos.contador51a100, "51-100", "Rango");
        dataset.addValue(AdministracionDeProductos.contador101a150, "101-150", "Rango");
        dataset.addValue(AdministracionDeProductos.contador151a200, "151-200", "Rango");
        dataset.addValue(AdministracionDeProductos.contador201a1000, "201-1000", "Rango");
        return dataset;
    }


}



