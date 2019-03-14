package isima.caillou;

import org.json.JSONObject;

public class Response {
    private final Product product;

    public Response(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
