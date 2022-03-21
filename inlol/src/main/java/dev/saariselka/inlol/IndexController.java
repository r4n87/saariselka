package dev.saariselka.inlol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("")
    public ModelAndView getMain() {
        logger.info("get Main");
        ModelAndView modelAndView = new ModelAndView();

        Summoner summoner = new Summoner();
        modelAndView.addObject("summoner", summoner);

        modelAndView.setViewName("summoner-thyme");

        return modelAndView;
    }
}
