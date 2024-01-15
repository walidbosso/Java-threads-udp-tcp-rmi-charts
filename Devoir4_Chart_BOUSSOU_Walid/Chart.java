package chart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jfree.chart.*;//ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart extends JFrame {

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "C:\\Users\\packardbell\\Desktop\\Data_temp.csv";

        Chart example = new Chart();
        String series1 = "chart";
        String series2 = "chart2";
        String[] time = new String[20];
        time[0] = "7:10 pm";
        time[1] = "7:40 pm";
        time[2] = "8;00 pm";
        time[3] = "8:20 pm";
        time[4] = "8:50 pm";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        int i = 0;
        while (sc.hasNext()) {
            dataset.addValue(Float.parseFloat(sc.next()), series1, time[i]);
            i++;
        }
        sc.close();

        JFreeChart chart = ChartFactory.createLineChart(
                "temperatue chart", // Chart title  
                "time", // X-Axis Label  
                "temp degree", // Y-Axis Label  
                dataset
        );
        ChartPanel panel = new ChartPanel(chart);
        example.setContentPane(panel);
        example.setAlwaysOnTop(true);
        example.setSize(600, 400);
        example.setVisible(true);

    }

}
