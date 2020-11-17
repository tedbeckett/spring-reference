package springref.adaper;

import org.springframework.stereotype.Component;
import springref.dto.WidgetDto;
import springref.model.Widget;

import java.util.function.Function;

@Component
public class WidgetAdapter implements Function<Widget, WidgetDto> {

    private Widget widget;

    public WidgetDto toDto(Widget widget) {
        return apply(widget);
    }

    public Widget toWidget(WidgetDto dto) {
        return new Widget(dto.getName(), dto.getSize());
    }

    // Lambda to convert Widget to WidgetDto.

    /**
     * Lambda that converts Widget to WidgetDto.
     */
    @Override
    public WidgetDto apply(Widget widget) {
        return new WidgetDto(widget.getName(), widget.getSize());
    }
}
