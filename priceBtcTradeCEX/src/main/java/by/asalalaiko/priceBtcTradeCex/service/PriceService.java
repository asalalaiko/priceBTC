package by.asalalaiko.priceBtcTradeCex.service;

import by.asalalaiko.priceBtcTradeCex.model.Price;
import org.springframework.stereotype.Service;


public interface PriceService {
    Price getPriceToTrader();
    void savePrice(Price price);
    Price findLastPrice();
}
