import dto.MeasurementDTO;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class DrawChart {

    public static void main(String[] args) {
        List<Double> temparatures = getTemperaturesFromServer();
        drawChart(temparatures);
    }

    private static List<Double> getTemperaturesFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";

        HttpEntity<Void> requestEntity = new HttpEntity<>(null);
        List<MeasurementDTO> measurementDTOs = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<MeasurementDTO>>() {}
        ).getBody();

        if (measurementDTOs == null) {
            return Collections.emptyList();
        }

        return measurementDTOs.stream()
                .map(MeasurementDTO::getValue)
                .collect(Collectors.toList());
    }

    private static void drawChart(List<Double> temperatures) {
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yData = temperatures.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperature Chart", "Index", "Value", "Temperature", xData, yData);

        new SwingWrapper(chart).displayChart();
    }
}