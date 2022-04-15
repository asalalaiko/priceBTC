package by.asalalaiko.priceBtcTradeCex.repo;


import by.asalalaiko.priceBtcTradeCex.model.Price;
import by.asalalaiko.priceBtcTradeCex.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Price findFirstByOrderByIdDesc();
}
