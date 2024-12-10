import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ExchangeRatesResponse {

    @SerializedName("rates")
    private Map<String, Double> rates;

    @SerializedName("base")
    private String baseCurrency;

    public Map<String, Double> getRates() {
        return rates;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }
}