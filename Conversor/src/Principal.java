import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Principal{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Obtener las tasas de cambio usando Gson
            ExchangeRatesResponse tasasDeCambio = obtenerTasasDeCambio();

            // Mostrar las monedas disponibles
            Map<String, Double> rates = tasasDeCambio.getRates();
            System.out.println("Monedas disponibles: ");
            for (String key : rates.keySet()) {
                System.out.println(key);  // Imprimir todas las claves de monedas disponibles
            }

            // Solicitar la moneda de origen
            System.out.print("Selecciona la moneda de origen: ");
            String monedaOrigen = scanner.nextLine().toUpperCase();

            // Validar que la moneda de origen esté en la lista de monedas disponibles
            if (!rates.containsKey(monedaOrigen)) {
                System.out.println("Moneda no válida. Fin del programa.");
                return;
            }

            // Solicitar la moneda de destino
            System.out.print("Selecciona la moneda de destino: ");
            String monedaDestino = scanner.nextLine().toUpperCase();

            // Validar que la moneda de destino esté en la lista
            if (!rates.containsKey(monedaDestino)) {
                System.out.println("Moneda no válida. Fin del programa.");
                return;
            }

            // Solicitar la cantidad para convertir
            System.out.print("Ingresa la cantidad a convertir: ");
            double cantidad = scanner.nextDouble();

            // Realizar la conversión
            double tasaOrigen = rates.get(monedaOrigen);
            double tasaDestino = rates.get(monedaDestino);
            double tasaConversion = tasaDestino / tasaOrigen;
            double cantidadConvertida = cantidad * tasaConversion;

            // Imprimir el resultado
            System.out.println(cantidad + " " + monedaOrigen + " es igual a " + cantidadConvertida + " " + monedaDestino);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Método para obtener las tasas de cambio de la API utilizando Gson
    private static ExchangeRatesResponse obtenerTasasDeCambio() throws Exception {
        String apiKey = "tu_api_key_aqui";  // Inserta tu API Key
        String url = "https://api.exchangerate-api.com/v4/latest/USD";  // URL para obtener las tasas de cambio

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)  // Agregar encabezado si es necesario
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Usamos Gson para convertir la respuesta JSON en un objeto de tipo ExchangeRatesResponse
        Gson gson = new Gson();
        return gson.fromJson(response.body(), ExchangeRatesResponse.class);
    }
}