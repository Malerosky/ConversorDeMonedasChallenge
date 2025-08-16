package conversor.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyService {
    private static final String API_KEY = "068748adf4ebb5d4c0e5adb8";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public JsonObject getRates() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(response.body(), JsonObject.class);

        return json.getAsJsonObject("conversion_rates");
    }

    public double convert(String from, String to, double amount, JsonObject rates) {
        double rateFrom = rates.get(from).getAsDouble();
        double rateTo = rates.get(to).getAsDouble();

        // Convertir primero a USD, luego a la moneda destino
        double amountInUSD = amount / rateFrom;
        return amountInUSD * rateTo;
    }
}
