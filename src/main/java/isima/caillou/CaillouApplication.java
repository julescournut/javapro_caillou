package isima.caillou;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CaillouApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaillouApplication.class, args);

		Unirest.setObjectMapper(new ObjectMapper() {
			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
					= new com.fasterxml.jackson.databind.ObjectMapper();

			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return jacksonObjectMapper.readValue(value, valueType);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			public String writeValue(Object value) {
				try {
					return jacksonObjectMapper.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}
		});

		getDataFromAPI("3029330003533");
	}

	private static void getDataFromAPI(String code) {
		// Response to Object
		try {
			JSONObject requestResponse = Unirest.get("https://fr.openfoodfacts.org/api/v0/produit/" + code + ".json").asJson().getBody().getObject();
			System.out.println(requestResponse.getJSONObject("product").getJSONObject("nutriments").getString("energy_100g"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
