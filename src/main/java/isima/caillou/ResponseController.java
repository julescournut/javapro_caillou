package isima.caillou;

import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResponseController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/product")
    public Response product(@RequestParam(value="code", defaultValue="3029330003533") String code) {
        List<Product> productList = new ArrayList<>();

        Product res = getDataFromAPI(code);
        res.setScore();

        productList.add(res);

        return new Response(productList);
    }

    @RequestMapping("/basket")
    public Response basket(@RequestParam(value="email", defaultValue="jules.cournut@hotmail.fr") String email, @RequestParam(value="numBasket", defaultValue="1") String numBasket) {

        List<Product> productList = new ArrayList<>();

        List<String> listCodeBar = productRepository.findByNumBasket(numBasket, email);

        for (String codeBar : listCodeBar) {
            Product res = getDataFromAPI(codeBar);
            res.setScore();
            productList.add(res);
        }


        return new Response(productList);
    }

    @RequestMapping("/addToBasket")
    public void addToBasket(@RequestParam(value="code", defaultValue="3029330003533") String code, @RequestParam(value="email", defaultValue="jules.cournut@hotmail.fr") String email, @RequestParam(value="numBasket", defaultValue="1") String numBasket) {
        productRepository.save(new BDProduct(email, numBasket, code));
    }

    @RequestMapping("/removeFromBasket")
    public void removeFromBasket(@RequestParam(value="code", defaultValue="3029330003533") String code, @RequestParam(value="email", defaultValue="jules.cournut@hotmail.fr") String email, @RequestParam(value="numBasket", defaultValue="1") String numBasket) {
        BDProduct product = productRepository.find(email, numBasket, code);
        if (product != null) {
            productRepository.delete(product);
        }
    }

    private static Product getDataFromAPI(String code) {
        Product p = new Product();
        // Response to Object
        try {
            JSONObject requestResponse = Unirest.get("https://fr.openfoodfacts.org/api/v0/produit/" + code + ".json").asJson().getBody().getObject();
            JSONObject o = requestResponse.getJSONObject("product").getJSONObject("nutriments");
            p.name = requestResponse.getJSONObject("product").getString("product_name_fr");
            p.codeBar = requestResponse.getJSONObject("product").getString("id");
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
