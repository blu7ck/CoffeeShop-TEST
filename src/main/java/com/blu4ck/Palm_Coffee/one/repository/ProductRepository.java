package com.blu4ck.Palm_Coffee.one.repository;

import com.blu4ck.Palm_Coffee.one.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // İhtiyaca göre özel sorgular eklenebilir
}
