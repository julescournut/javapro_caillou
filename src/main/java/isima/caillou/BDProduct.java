package isima.caillou;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="BDProduct")
public class BDProduct {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 50)
    @Column(name = "numbasket", length = 50)
    private String numBasket;

    @Size(max = 150)
    @Column(name = "codebarproduct", length = 150)
    private String codeBarProduct;

    public BDProduct() {}

    public BDProduct(String email, String numBasket, String codeBarProduct) {
        this.email = email;
        this.numBasket = numBasket;
        this.codeBarProduct = codeBarProduct;
    }

    @Override
    public String toString() {
        return "Bonjour dit ! : " + email + " ; " + numBasket + " ; " + codeBarProduct;
    }
}
