package springref.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import springref.ApplicationLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


// Makes this a Spring Java config class
@Configuration

// Scans for Spring annotation managed beans recursively starting with the package. Can also supply a class
// to scan starting with the package that holds the class.
@ComponentScan(basePackageClasses = ApplicationLauncher.class)

// Load property file
@PropertySource("classpath:/application.properties")

// @EnableWebMvc will automatically register a json converter if one is available,
// specifically, if Jackson is on the classpath, i.e. if Jackson is a maven dependency,
// a json converter is found and registered and objects are automatically converted
// to and from json.
@EnableWebMvc
public class AppConfig {

    // Convenient way to make a framework/library provided class available as a managed bean.
    // Default scope is singleton. Note ObjectMapper is thread-safe.
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());

        viewResolver.setOrder(1); // optional
        viewResolver.setViewNames(new String[] {"*.html", "*.xhtml"}); // optional
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
}
