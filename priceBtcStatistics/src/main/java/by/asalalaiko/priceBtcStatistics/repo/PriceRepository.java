package by.asalalaiko.priceBtcStatistics.repo;


import by.asalalaiko.priceBtcStatistics.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price>findByTimestampBetween(Timestamp timestampStart, Timestamp timestampFinish);

    Price findFirstByOrderByIdDesc();
}
