package by.asalalaiko.priceBtcStatistics.repo;


import by.asalalaiko.priceBtcStatistics.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {
    public Trader findByName(String name);
}
