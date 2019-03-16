package isima.caillou;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<BDProduct, Long> {
    @Query (value = "select codebarproduct from BDProduct where numbasket = ?1 and email = ?2", nativeQuery = true)
    List<String> findByNumBasket(String numBasket, String email);

    @Query (value = "select * from BDProduct where numbasket = ?2 and email = ?1 and codebarproduct = ?3 limit 1", nativeQuery = true)
    BDProduct find(String email, String numBasket, String codeBar);
}
