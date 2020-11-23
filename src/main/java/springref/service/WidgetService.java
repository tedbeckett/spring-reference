package springref.service;

import springref.dto.WidgetDto;
import springref.model.Widget;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WidgetService {

    private List<Widget> widgets = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void init() {
        widgets.add(new Widget("Sprocket", 1));
        widgets.add(new Widget("Bracket", 2));
    }

    public void addWidget(Widget widget) {
        widgets.add(widget);
    }

    public List<Widget> getWidgets() {
        return new ArrayList(widgets);
    }
}
