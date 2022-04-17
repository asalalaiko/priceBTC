package by.asalalaiko.priceBtcStatistics.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String Home (){

        System.out.println();
        return "index";
    }


}
