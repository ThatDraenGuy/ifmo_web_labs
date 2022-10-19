package beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.map.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Named("graphBean")
@SessionScoped
public class GraphBean implements Serializable {
    @Getter
    private LineChartModel lineChartModel;

    public GraphBean() {
        lineChartModel = new LineChartModel();
        stuff();
    }
    public void stuff() {
        System.out.println("setting up");
        ChartData data = new ChartData();
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        dataSet.setData(numbers);
        dataSet.setBackgroundColor("blue");
        dataSet.setBorderColor("blue");
        dataSet.setShowLine(true);
        dataSet.setFill(false);
        dataSet.setLabel("Acme Gross Incomes");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setTension(0.1);

        data.addChartDataSet(dataSet);

        data.setLabels(List.of("test", "stuff", "man"));

        lineChartModel.setData(data);
    }
}
