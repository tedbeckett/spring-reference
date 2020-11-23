package springref.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springref.adaper.WidgetAdapter;
import springref.dto.WidgetDto;
import springref.model.Widget;
import springref.service.WidgetService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Example of a REST API controller.
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final WidgetService widgetService;
    private final WidgetAdapter widgetAdapter;

    public ApiController(WidgetService widgetService, WidgetAdapter widgetAdapter) {
        this.widgetService = widgetService;
        this.widgetAdapter = widgetAdapter;
    }

    @GetMapping("/widgets")
    public List<WidgetDto> getWidgets() throws IOException {
        List<WidgetDto> widgetDtos = widgetService.getWidgets()
                .stream()
                .map(widgetAdapter)
                .collect(Collectors.toList());
        return widgetDtos;
    }

    @PostMapping("/widget")
    public void addWidget(@RequestBody WidgetDto widgetDto) {
        log.debug("ApiController: addWidget: " + widgetDto);
        Widget widget = widgetAdapter.toWidget(widgetDto);
        widgetService.addWidget(widget);
    }
}
