package springref.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import springref.adaper.WidgetAdapter;
import springref.context.AppConfig;
import springref.model.Widget;
import springref.service.WidgetService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// SpringExtension adds Junit functionality like @Before and @Test to Spring based code.
@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = AppConfig.class)

// Required to get the test to come up since controllers are on the scan path
@WebAppConfiguration
class ApiControllerTest {
    @Autowired
    private WidgetService widgetService;

    @Autowired
    private ApiController apiController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    void getWidgets() throws Exception {
        // First do the test in a style that allows for comments for someone unfamiliar with the testframework.

        // Build the request
        MockHttpServletRequestBuilder builder = get("/api/widgets").accept(MediaType.APPLICATION_JSON);

        // Perform the request, getting back an object which allows verifying the result.
        ResultActions resultActions = mockMvc.perform(builder);

        // Now create and pass ResultMatchers to ResultActions to verify response fields.
        resultActions.andExpect(status().isOk());

        // json() is from JSONAssert. If not using strict mode, which is recommended,
        // it only compares the fields in the object returned to the fields explicitly provided, allowing fields which
        // like modification times or server generated identifiers, to be ignored in the comparison.
        resultActions.andExpect(content().json("[ {name:\"Sprocket\", size:1 }, { name:\"Bracket\", size:2 } ]"));

        // Now do the test in the normal, fluid style.
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/widgets")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json("[ {name:\"Sprocket\", size:1 }, { name:\"Bracket\", size:2 } ]"));
    }
}
