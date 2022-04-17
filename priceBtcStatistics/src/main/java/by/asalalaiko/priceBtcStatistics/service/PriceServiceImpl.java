package by.asalalaiko.priceBtcStatistics.service;

import by.asalalaiko.priceBtcStatistics.model.Price;
import by.asalalaiko.priceBtcStatistics.repo.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

import static java.time.temporal.TemporalAdjusters.*;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class PriceServiceImpl implements PriceService {

    LocalDate now = LocalDate.now();
    public Timestamp timestampStart = Timestamp.valueOf(now.atStartOfDay());
    public Timestamp timestampFinish = Timestamp.valueOf(now.atTime(LocalTime.MAX));
    public Timestamp timestampMountStart = Timestamp.valueOf(now.with(firstDayOfMonth()).atStartOfDay());
    public Timestamp timestampMountFinish = Timestamp.valueOf(now.with(lastDayOfMonth()).atTime(LocalTime.MAX));
    public Timestamp timestampYearStart =Timestamp.valueOf(now.with(firstDayOfYear()).atStartOfDay());
    public Timestamp timestampYearEnd =Timestamp.valueOf(now.with(lastDayOfYear()).atTime(LocalTime.MAX));


    @Autowired
    PriceRepository priceRepository;

    @Override
    public Price findMinPriceToday() {
        return priceRepository.findByTimestampBetween(timestampStart,timestampFinish)
                .stream().min(Comparator.comparing(Price::getPrice)).get();
    }

    @Override
    public Price findMaxPriceToday() {
        return priceRepository.findByTimestampBetween(timestampStart,timestampFinish)
                .stream().max(Comparator.comparing(Price::getPrice)).get();
    }

    @Override
    public Price findMinPriceMount() {
        return priceRepository.findByTimestampBetween(timestampMountStart,timestampMountFinish)
                .stream().min(Comparator.comparing(Price::getPrice)).get();
    }

    @Override
    public Price findMaxPriceMount() {
        return priceRepository.findByTimestampBetween(timestampMountStart,timestampMountFinish)
                .stream().max(Comparator.comparing(Price::getPrice)).get();
    }

    @Override
    public Price findMinPriceYear() {
        return priceRepository.findByTimestampBetween(timestampYearStart,timestampYearEnd)
                .stream().min(Comparator.comparing(Price::getPrice)).get();
    }

    @Override
    public Price findMaxPriceYear() {
        return priceRepository.findByTimestampBetween(timestampYearStart,timestampYearEnd)
                .stream().max(Comparator.comparing(Price::getPrice)).get();
    }


}
