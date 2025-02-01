package gr.alexc.fleetatimportmapmfeserver.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/importmap.json")
                .addResourceLocations("file:" + env.getProperty("importmap.importmap-location"))
                .setCachePeriod(10) // TODO: I need to check that cache if it is good
                .resourceChain(true);

        registry
                .addResourceHandler("/packages/**")
                .addResourceLocations("file:" + env.getProperty("importmap.packages-location"))
                .setCachePeriod(3600)
                .resourceChain(true);

    }

}
