package by.asalalaiko.priceBtcTradeCex;

import by.asalalaiko.priceBtcTradeCex.model.Trader;
import by.asalalaiko.priceBtcTradeCex.repo.TraderRepository;
import by.asalalaiko.priceBtcTradeCex.scheduling.ScheduledTask;
import by.asalalaiko.priceBtcTradeCex.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriceBtcTradeCexApplication implements CommandLineRunner {

	@Autowired
	private TraderRepository traderRepository;
	@Autowired
	private PriceService priceService;

	@Value("${trader.name}")
	private String traderName;
	@Value("${trader.description}")
	private String traderDescription;

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceBtcTradeCexApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(PriceBtcTradeCexApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception
	{
		if (traderRepository.findByName(traderName)==null)
		{
			try {
				Trader trader = new Trader();
				trader.setName(traderName);
				trader.setDescription(traderDescription);
				traderRepository.save(trader);
				LOGGER.info("Trader save - "+trader.toString());
			} catch (Exception e) {
				LOGGER.error("Error creating trader ", e);
			}

		}

		System.out.println(priceService.findLastPrice().toString());

	}

}
