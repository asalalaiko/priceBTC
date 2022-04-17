package by.asalalaiko.priceBtcStatistics;

import by.asalalaiko.priceBtcStatistics.model.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriceBtcStatisticsApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceBtcStatisticsApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(PriceBtcStatisticsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Price minPrice = new Price();
			minPrice.setPrice(2.22);
			Price maxPrice = new Price();
			maxPrice.setPrice(9.99);


		} catch (Exception e) {
			LOGGER.error("Error start Statistics Application ", e);
		}

	}
}
