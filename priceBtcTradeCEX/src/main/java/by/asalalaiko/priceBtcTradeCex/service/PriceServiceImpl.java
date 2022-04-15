package by.asalalaiko.priceBtcTradeCex.service;

import by.asalalaiko.priceBtcTradeCex.PriceBtcTradeCexApplication;
import by.asalalaiko.priceBtcTradeCex.model.LastPrice;
import by.asalalaiko.priceBtcTradeCex.model.Price;
import by.asalalaiko.priceBtcTradeCex.model.Trader;
import by.asalalaiko.priceBtcTradeCex.repo.PriceRepository;
import by.asalalaiko.priceBtcTradeCex.repo.TraderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private TraderRepository traderRepository;

    @Value("${trader.name}")
    private String traderName;
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceServiceImpl.class);
    @Async
    @Override
    public Price getPriceToTrader() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        Price price = new Price();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LastPrice lastPrice = restTemplate.getForObject("https://cex.io/api/last_price/BTC/USD", LastPrice.class);

        price.setPrice(lastPrice.getLprice());
        price.setTimestamp(timestamp);
        price.setTraderId(traderRepository.findByName(traderName).getId());
        return price;
    }

    @Override
    public void savePrice(Price price) {
        priceRepository.save(price);
    }

    @Override
    public Price findLastPrice() {
        Price price = priceRepository.findFirstByOrderByIdDesc();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (price==null){
            price = new Price();
            price.setTimestamp(timestamp);
            price.setPrice(0.00);
            price.setId(0);
            price.setTraderId(traderRepository.findByName(traderName).getId());
        }
        return price;
    }
}
