package isima.caillou;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private final List<Product> products;
    private final List<String> qualitesPanier;
    private final List<String> defautsPanier;

    public Response(List<Product> products) {
        this.products = products;
        qualitesPanier = new ArrayList<>();
        defautsPanier = new ArrayList<>();
        for (Product p : products) {
            for (String qualite : p.qualites) {
                if (!qualitesPanier.contains(qualite)) {
                    qualitesPanier.add(qualite);
                }
            }
            for (String defaut : p.defauts) {
                if (!defautsPanier.contains(defaut)) {
                    defautsPanier.add(defaut);
                }
            }
        }
    }

    public List<Product> getProducts() { return products;    }
    public List<String> getQualitesPanier() { return qualitesPanier;    }
    public List<String> getDefautsPanier() { return defautsPanier;    }
}
