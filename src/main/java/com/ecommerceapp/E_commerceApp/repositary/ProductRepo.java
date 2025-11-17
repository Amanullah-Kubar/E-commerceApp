package com.ecommerceapp.E_commerceApp.repositary;

import com.ecommerceapp.E_commerceApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(p.category) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(p.brand) LIKE CONCAT('%', LOWER(:query), '%') OR " +
            "LOWER(p.description) LIKE CONCAT('%', LOWER(:query), '%')")
    List<Product> getProductByQuery(@Param("query") String query);

}
