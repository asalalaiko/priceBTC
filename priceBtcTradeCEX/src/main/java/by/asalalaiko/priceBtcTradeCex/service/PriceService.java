package by.asalalaiko.priceBtcTradeCex.service;

import by.asalalaiko.priceBtcTradeCex.model.Price;
import org.springframework.stereotype.Service;


public interface PriceService {
    public Price getPriceToTrader();
    public void savePrice(Price price);
}
