package jpabook.jpabook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j //log.info
public class HomeController {
    @RequestMapping("/")
    public String home() {
        log.info("---Home---Controller--started");
        return "home";
    }
}
