package springref.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springref.context.AppConfig;
import springref.model.Widget;
import springref.service.WidgetService;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// SpringExtension adds Junit functionality like @Before and @Test to Spring based code.
@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = AppConfig.class)

// Required to get the test to come up since controllers are on the scan path
@WebAppConfiguration
class UIControllerTest {

    @Autowired
    private WidgetService widgetService;

    @Autowired
    UIController uiController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(uiController).build();
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/home")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("index.html"));
    }

    @Test
    void testWidgets() throws Exception {
        Widget[] expectedWidgets = widgetService.getWidgets().toArray(new Widget[0]);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/widgets")
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("widgets", containsInAnyOrder(expectedWidgets)));
    }
}