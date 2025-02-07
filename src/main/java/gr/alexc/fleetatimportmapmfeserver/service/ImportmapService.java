package gr.alexc.fleetatimportmapmfeserver.service;

import org.springframework.stereotype.Service;

@Service
public interface ImportmapService {

    void getImportmap();

    Object updateImportmap(Object importmap);

}
