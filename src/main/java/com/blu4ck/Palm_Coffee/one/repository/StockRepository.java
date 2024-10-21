package com.blu4ck.Palm_Coffee.one.repository;

import com.blu4ck.Palm_Coffee.one.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    // İhtiyaca göre özel sorgular eklenebilir
}
