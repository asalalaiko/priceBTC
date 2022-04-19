package by.asalalaiko.priceBtcStatistics.service;


import by.asalalaiko.priceBtcStatistics.model.Price;

public interface PriceService {
    //min at 24 hours
    Price findMinPriceToday();
    //max at 24 hours
    Price findMaxPriceToday();
    //min at month
    Price findMinPriceMonth();
    //max at month
    Price findMaxPriceMonth();
    //min at year
    Price findMinPriceYear();
    //max at year
    Price findMaxPriceYear();





}
