package by.asalalaiko.priceBtcTradeCex.scheduling;

import by.asalalaiko.priceBtcTradeCex.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    private PriceService priceService;


    @Scheduled(cron = "0 * * * * *")
    public void getPrises(){
        System.out.println(priceService.getPriceToTrader());
    }
}
