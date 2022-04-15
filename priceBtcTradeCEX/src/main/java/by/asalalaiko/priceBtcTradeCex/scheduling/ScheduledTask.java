package by.asalalaiko.priceBtcTradeCex.scheduling;

import by.asalalaiko.priceBtcTradeCex.model.Price;
import by.asalalaiko.priceBtcTradeCex.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    private PriceService priceService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);


    @Scheduled(cron = "0 * * * * *")
    public void getPrises(){
        try {
            Price lprice = priceService.findLastPrice();

            Price price = priceService.getPriceToTrader();

            if (price!=null && !(lprice.getPrice().equals(price.getPrice()))) {
                priceService.savePrice(price);
                LOGGER.info("Find & save - "+price.toString());
             } else {
                LOGGER.info("Find: "+price.toString()+" Last price:"+lprice.toString());
            }



            } catch (Exception e) {
        LOGGER.error("Error running Task(getPrises)-", e);
        }

        }
}
