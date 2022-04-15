package by.asalalaiko.priceBtcTradeCex;

import by.asalalaiko.priceBtcTradeCex.model.Trader;
import by.asalalaiko.priceBtcTradeCex.repo.TraderRepository;
import by.asalalaiko.priceBtcTradeCex.service.PriceService;
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


	public static void main(String[] args) {
		SpringApplication.run(PriceBtcTradeCexApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception
	{
		if (traderRepository.findByName(traderName)==null)
		{
		Trader trader = new Trader();
		trader.setName(traderName);
		trader.setDescription(traderDescription);
		traderRepository.save(trader);
		}



	}

}
