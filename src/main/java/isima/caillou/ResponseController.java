package isima.caillou;

import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @RequestMapping("/response")
    public Response product(@RequestParam(value="code", defaultValue="3029330003533") String code) {

        Product res = getDataFromAPI(code);
        res.setScore();

        return new Response(res);
    }

    // Todo Ne plus retourner une enorme string mais un objet JSON propre comme le cul d'un babouin


    private static Product getDataFromAPI(String code) {
        Product p = new Product();
        // Response to Object
        try {
            JSONObject requestResponse = Unirest.get("https://fr.openfoodfacts.org/api/v0/produit/" + code + ".json").asJson().getBody().getObject();
            JSONObject o = requestResponse.getJSONObject("product").getJSONObject("nutriments");
            p.name = requestResponse.getJSONObject("product").getString("product_name_fr");
            p.nutriments.energy_100g = o.has("energy_100g") ? Integer.parseInt(o.getString("energy_100g")) : 0;
            p.nutriments.fiber_100g = o.has("fiber_100g") ? o.getDouble("fiber_100g") : 0;
            p.nutriments.proteins_100g = o.has("proteins_100g") ? o.getDouble("proteins_100g") : 0;
            p.nutriments.salt_100g = o.has("salt_100g") ? o.getDouble("salt_100g") : 0;
            p.nutriments.saturated_fat_100g = o.has("saturated-fat_100g") ? o.getDouble("saturated-fat_100g") : 0;
            p.nutriments.sugars_100g = o.has("sugars_100g") ? o.getDouble("sugars_100g") : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
