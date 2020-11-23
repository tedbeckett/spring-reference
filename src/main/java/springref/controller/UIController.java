package springref.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springref.model.Widget;
import springref.service.WidgetService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UIController {

    private final WidgetService widgetService;

    public UIController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

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

    @GetMapping("/widgets")
    public String widgets(Model model) {
        List<Widget> widgets = widgetService.getWidgets();
        model.addAttribute("widgets", widgets);
        return "widgets.html";
    }

}
