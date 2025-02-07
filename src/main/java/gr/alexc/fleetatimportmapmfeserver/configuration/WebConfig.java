package gr.alexc.fleetatimportmapmfeserver.configuration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import java.io.IOException;
import java.util.List;

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
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

//        registry
//                .addResourceHandler("/")
////                .addResourceLocations("file:" + env.getProperty("importmap.core-package-location"))
//                .setCachePeriod(3600)
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver() {
//                    @Override
//                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
//                        return new PathResource(env.getProperty("importmap.core-package-location") + "/index.html");
//                    }
//                });

        registry
                .addResourceHandler("", "/", "/**")
                .addResourceLocations("file:" + env.getProperty("importmap.core-package-location"))
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {

//                    @Override
//                    public Resource resolveResource(HttpServletRequest request, String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {
//                        return super.resolveResource(request, requestPath, locations, chain);
//                    }
//
//                    @Override
//                    public String resolveUrlPath(String resourceUrlPath, List<? extends Resource> locations, ResourceResolverChain chain) {
//                        return super.resolveUrlPath(resourceUrlPath, locations, chain);
//                    }
//
//                    @Override
//                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
//                        Resource requestedResource = location.createRelative(resourcePath);
//                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
//                                : new PathResource(env.getProperty("importmap.core-package-location") + "/index.html");
//                    }
                });

    }

}
