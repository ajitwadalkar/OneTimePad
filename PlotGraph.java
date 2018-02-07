
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;

public class PlotGraph extends Application {

    @Override public void start(Stage stage) {

        HashMap<BigInteger, Integer> uniqueKey = new HashMap<>();
        for(int i=0;i<20000;i++)
        {
           BigInteger newSKey = new BigInteger(3, new Random());
            if(uniqueKey.containsKey(newSKey))
            {
                uniqueKey.put(newSKey,(uniqueKey.get(newSKey)+1));
            }
            else {
                uniqueKey.put(newSKey, 1);
            }
        }
        stage.setTitle("DSP_Assignment1");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<>(xAxis,yAxis);
        bc.setTitle("Frequency Distribution");
        xAxis.setLabel("Frequency");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Lambda: "+3 );
        for (BigInteger key:uniqueKey.keySet()) {
            series1.getData().add(new XYChart.Data(key.toString(), uniqueKey.get(key)));
        }
        Scene scene  = new Scene(bc,800,400);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}