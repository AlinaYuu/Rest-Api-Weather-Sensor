import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {

    private static final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080";
    private static final Random random = new Random();

    static {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public static void main(String[] args) {
        String sensorName = "TestSensor";
        addSensor(sensorName);

        double minTemperature = -10.0;
        double maxTemperature = 30.0;

        for (int i = 0; i < 100; i++) {
            addMeasurement(random.nextDouble() * (maxTemperature - minTemperature) + minTemperature,
                    random.nextBoolean(), sensorName);
        }
    }

    public static void getSensors() {
        String url = BASE_URL + "/sensor";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }

    public static void getMeasurements() {
        String url = BASE_URL + "/measurements";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }

    public static void addSensor(String name) {
        String url = BASE_URL + "/sensor/registration";
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("name", name);

        sendPostRequest(url, requestMap, "sensor has been added");
    }

    public static void addMeasurement(double value, boolean raining, String sensorName) {
        String url = BASE_URL + "/measurements";
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("value", String.valueOf(value));
        requestMap.put("raining", String.valueOf(raining));
        requestMap.put("sensor", sensorName);

        sendPostRequest(url, requestMap, "measurement was sent to the server");
    }

    private static void sendPostRequest(String url, Map<String, String> requestMap, String successMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestMap, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println(successMessage);
        } catch (HttpClientErrorException e) {
            System.out.println("ОШИБКА!");
            System.out.println(e.getMessage());
        }
    }
}
