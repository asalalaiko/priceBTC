package by.asalalaiko.priceBtcTradeCex.service;

import by.asalalaiko.priceBtcTradeCex.model.Price;
import by.asalalaiko.priceBtcTradeCex.model.Trader;
import by.asalalaiko.priceBtcTradeCex.repo.PriceRepository;
import by.asalalaiko.priceBtcTradeCex.repo.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        price = restTemplate.getForObject("https://cex.io/api/last_price/BTC/USD", Price.class);
        price.setTimestamp(timestamp);
        return price;
    }

    @Override
    public void savePrice(Price price) {
        priceRepository.save(price);
    }
}
