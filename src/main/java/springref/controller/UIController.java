package springref.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Controller
public class UIController {

    // @PostConstruct method is called after dependencies have been injected.
    @PostConstruct
    public void completeInitialization() {
        // Do final initialization work here.
    }

    // @PreDestroy called when the application shuts down gracefully
    @PreDestroy
    public void cleanup() {
        // For this to get called the application must be shutdown (not terminated) and for a web app,
        // a shutdown hook must be registered.
    }

    @GetMapping("/home")
    public String home() {
        return "index.html";
    }
}
