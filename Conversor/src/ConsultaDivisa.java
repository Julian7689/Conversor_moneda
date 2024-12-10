import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaDivisa {

    // Método que obtiene las tasas de cambio en formato JSON usando Gson
    public static JsonObject obtenerTasasDeCambio() throws Exception {
        String apiKey = "tu_api_key_aqui";  // Cambia esto con tu clave de API
        String url = "https://api.exchangerate-api.com/v4/latest/USD";  // URL de la API

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)  // Agregar el encabezado de la API Key si es necesario
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Usamos Gson para parsear la respuesta JSON
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        return jsonResponse;
    }
}
