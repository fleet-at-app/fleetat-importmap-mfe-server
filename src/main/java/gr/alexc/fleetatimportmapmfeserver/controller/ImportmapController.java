package gr.alexc.fleetatimportmapmfeserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.apache.juli.FileHandler.DEFAULT_BUFFER_SIZE;

@Controller
@RequiredArgsConstructor
public class ImportmapController {

    private final Environment env;

// TODO: I need to implement something like this for fallback
//    @RequestMapping(value = "/{[path:[^\\.]*}")
//    public void redirect(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/");
//    }


    @GetMapping("/")
    public ResponseEntity<? extends Object> redirectToHome(HttpServletRequest request, HttpServletResponse response) {
        try {
            String indexFilePath = env.getProperty("importmap.core-package-location") + "/index.html";
            Path filePath = Paths.get(indexFilePath).toAbsolutePath();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                // TODO: I need to enable cache for this request. It will be very demanding...
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=index.html")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(
            value = {"api/importmap"},
            produces = "application/importmap+json"
    )
    public ResponseEntity<String> getImportmap() {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/importmap+json"));

        return new ResponseEntity<>("{\"imports\": []}", httpHeaders, HttpStatus.OK);
    }

}
