package org.ml.shipping.testing;

import org.ml.shipping.id.ProductId;
import org.ml.shipping.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductStockDAO extends JpaRepository<ProductStock, ProductId> {
     long countByDeposit(String deposit);

    @Query(value = "SELECT SUM(p.quantity) FROM ProductStock p WHERE p.location = ?1 GROUP BY p.location")
    String sumAmountInLocation(String deposit);

    @Query("SELECT DISTINCT p.productId FROM ProductStock p where p.location = ?1 AND p.deposit = ?2")
    List<String> findDistinctByProductIdAndLocationEquals(String location, String deposit);

    List<ProductStock> findByDepositAndLocation(String deposit, String location);

    List<ProductStock> findByDepositAndProductId(String deposit, String productId);

}
