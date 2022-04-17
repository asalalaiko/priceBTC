package by.asalalaiko.priceBtcStatistics;

import by.asalalaiko.priceBtcStatistics.model.Price;
import by.asalalaiko.priceBtcStatistics.repo.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;

import static java.time.temporal.TemporalAdjusters.*;

@SpringBootApplication
public class PriceBtcStatisticsApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceBtcStatisticsApplication.class);

	@Autowired
	PriceRepository priceRepository;

	public static void main(String[] args) {
		SpringApplication.run(PriceBtcStatisticsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {

			System.out.println("****************************************");
			LocalDate now = LocalDate.now();
			System.out.println("Today - "+ now);
			Timestamp timestampStart = Timestamp.valueOf(now.atStartOfDay());
			System.out.println("Start = "+ timestampStart);
			Timestamp timestampFinish = Timestamp.valueOf(now.atTime(LocalTime.MAX));
			System.out.println("End = "+ timestampFinish);
			Timestamp timestampMountStart = Timestamp.valueOf(now.with(firstDayOfMonth()).atStartOfDay());
			System.out.println("This mount start = " +timestampMountStart );
			Timestamp timestampMountFinish = Timestamp.valueOf(now.with(lastDayOfMonth()).atTime(LocalTime.MAX));


			System.out.println("This mount end = "+timestampMountFinish);
			Timestamp timestampYearStart =Timestamp.valueOf(now.with(firstDayOfYear()).atStartOfDay());
			System.out.println("This year start = " +timestampYearStart );
			Timestamp timestampYearEnd =Timestamp.valueOf(now.with(lastDayOfYear()).atTime(LocalTime.MAX));
			System.out.println("This year end = " +timestampYearEnd );



			System.out.println("****************************************");
			System.out.println(priceRepository.findByTimestampBetween(timestampMountStart,timestampFinish)
			.stream().min(Comparator.comparing(Price::getPrice)));
			System.out.println(priceRepository.findByTimestampBetween(timestampMountStart,timestampFinish)
					.stream().max(Comparator.comparing(Price::getPrice)));
			System.out.println("****************************************");
			System.out.println(priceRepository.findByTimestampBetween(timestampMountStart,timestampFinish));
		} catch (Exception e) {
			LOGGER.error("Error start Statistics Application ", e);
		}

	}
}
