package pl.mk.variety_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mariusz Kowalczuk
 */

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {

        return "index";

    }
}