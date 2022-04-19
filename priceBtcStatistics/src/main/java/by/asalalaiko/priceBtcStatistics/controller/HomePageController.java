package by.asalalaiko.priceBtcStatistics.controller;


import by.asalalaiko.priceBtcStatistics.model.Price;
import by.asalalaiko.priceBtcStatistics.repo.PriceRepository;
import by.asalalaiko.priceBtcStatistics.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.TemporalAdjusters.*;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Controller
public class HomePageController {

    @Autowired
    PriceService priceService;

    @GetMapping("/")
    public String Home (Model model){

        Price minPriceToday = priceService.findMinPriceToday();
        Price maxPriceToday = priceService.findMaxPriceToday();
        Price minPriceMonth = priceService.findMinPriceMonth();
        Price maxPriceMonth = priceService.findMaxPriceMonth();
        Price minPriceYear = priceService.findMinPriceYear();
        Price maxPriceYear = priceService.findMaxPriceYear();


        model.addAttribute("title", "All statistics");
        model.addAttribute("minPriceToday", minPriceToday);
        model.addAttribute("maxPriceToday", maxPriceToday);
        model.addAttribute("minPriceMonth", minPriceMonth);
        model.addAttribute("maxPriceMonth", maxPriceMonth);
        model.addAttribute("minPriceYear", minPriceYear);
        model.addAttribute("maxPriceYear", maxPriceYear);
        return "index";
    }

    @GetMapping("/day")
    public String day(Model model) {
        Price minPriceToday = priceService.findMinPriceToday();
        Price maxPriceToday = priceService.findMaxPriceToday();
        model.addAttribute("title", "Day statistics");
        model.addAttribute("minPriceToday", minPriceToday);
        model.addAttribute("maxPriceToday", maxPriceToday);
        return "day";
    }

        @GetMapping("/month")
        public String month(Model model) {
            Price minPriceMonth = priceService.findMinPriceMonth();
            Price maxPriceMonth = priceService.findMaxPriceMonth();
            model.addAttribute("title", "Month statistics");
            model.addAttribute("minPriceMonth", minPriceMonth);
            model.addAttribute("maxPriceMonth", maxPriceMonth);
            return "month";
    }

        @GetMapping("/year")
        public String year(Model model) {
            Price minPriceYear = priceService.findMinPriceYear();
            Price maxPriceYear = priceService.findMaxPriceYear();
            model.addAttribute("title", "Year statistics");
            model.addAttribute("minPriceYear", minPriceYear);
            model.addAttribute("maxPriceYear", maxPriceYear);
            return "year";
        }

}
