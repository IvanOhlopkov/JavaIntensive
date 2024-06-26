package weather.services;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import mycollections.MyHashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.entity.City;
import weather.repositories.CityRepository;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final String API_KEY = "65ef04686a832111303907isnf459ab";
    private static final String WEATHER_OPTIONS = "temperature_2m";
    private static final String TIMEZONE = "Europe%2FMoscow";
    private static final String DAYS_FORECAST = "1";

    CityRepository cityRepository;

    public WeatherServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public String getTemp(String city) {

        try {
            HttpResponse<String> responseWeather = getResponseWeather(city);
            return parseJsonWeather(responseWeather.body());
        } catch (ParseException | URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<String> getResponseWeather(String inputCity) throws URISyntaxException, IOException, InterruptedException, ParseException {

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest requestLatitudeLongitudeCity = HttpRequest
                .newBuilder(new URI("https://geocode.maps.co/search?q="
                        + inputCity + "&api_key=" + API_KEY))
                .build();

        HttpResponse<String> response = client
                .send(requestLatitudeLongitudeCity, HttpResponse.BodyHandlers.ofString());

        City city = parseJsonLatitudeLongitude(response.body());
        city.setCity(inputCity);

        HttpRequest requestWeatherForecast = HttpRequest
                .newBuilder(new URI("https://api.open-meteo.com/v1/forecast?latitude=" +
                        city.getLatitude() +
                        "&longitude=" +
                        city.getLongitude() +
                        "&hourly=" + WEATHER_OPTIONS +
                        "&timezone=" + TIMEZONE +
                        "&forecast_days=" + DAYS_FORECAST))
                .build();
        cityRepository.save(city);

        return client
                .send(requestWeatherForecast, HttpResponse.BodyHandlers.ofString());
    }

    private City parseJsonLatitudeLongitude(String body) throws ParseException {
        String latitude = "";
        String longitude = "";

        Object obj = new JSONParser().parse(body);
        JSONArray jsonArray = (JSONArray) obj;

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            if (jsonObject.get("type").equals("city")) {
                latitude = jsonObject.get("lat").toString();
                longitude = jsonObject.get("lon").toString();
                break;
            }
        }

        City city = new City();
        city.setLatitude(latitude);
        city.setLongitude(longitude);
        return city;
    }

    private String parseJsonWeather(String body) throws ParseException {

        Object obj = new JSONParser().parse(body);
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("hourly");

        JSONArray jsonArrayTime = (JSONArray) jsonObject1.get("time");
        JSONArray jsonArrayTemperature = (JSONArray) jsonObject1.get("temperature_2m");

        String timeForm = LocalDateTime.now().toString().substring(0, 14).concat("00");

        MyHashMap<String, String> myHashMap = new MyHashMap<>();


        for (int i = 0; i < 24; i++) {
            myHashMap.put(jsonArrayTime.get(i).toString(),jsonArrayTemperature.get(i).toString());
        }

        return myHashMap.get(timeForm);
    }


}
