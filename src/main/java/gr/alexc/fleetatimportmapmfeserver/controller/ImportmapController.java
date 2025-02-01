package gr.alexc.fleetatimportmapmfeserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImportmapController {

    @GetMapping(
            value = {},
            produces = "application/importmap+json"
    )
    public ResponseEntity<String> getImportmap() {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/importmap+json"));

        return new ResponseEntity<>("{\"imports\": []}", httpHeaders, HttpStatus.OK);
    }

}
