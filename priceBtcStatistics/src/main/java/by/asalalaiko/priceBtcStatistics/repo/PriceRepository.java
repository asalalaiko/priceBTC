package by.asalalaiko.priceBtcStatistics.repo;


import by.asalalaiko.priceBtcStatistics.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Price findFirstByOrderByIdDesc();
}
