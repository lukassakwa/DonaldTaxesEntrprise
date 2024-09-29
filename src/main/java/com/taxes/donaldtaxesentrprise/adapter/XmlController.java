package com.taxes.donaldtaxesentrprise.adapter;

import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePayloadResponse;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePyloadRequest;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GenerationStatus;
import com.taxes.donaldtaxesentrprise.domain.file.FileService;
import com.taxes.donaldtaxesentrprise.domain.gateway.in.GeneratorService;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class XmlController {
    private final ApplicationContext applicationContext;
    private final FileService fileService;

    @PostMapping("/generate")
    ResponseEntity<GeneratePayloadResponse> generate(GeneratePyloadRequest request) {
        try {
            GeneratePayloadResponse payload = getGeneratorService().generateXml(request);
            return ResponseEntity.ok(payload);
        } catch (Exception e) {
            GeneratePayloadResponse payload = GeneratePayloadResponse.builder()
                    .status(GenerationStatus.FAILURE)
                    .message(e.toString())
                    .build();
            return ResponseEntity.badRequest().body(payload);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "mock.xml");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        InputStreamResource inputStream = null;
        try {
            inputStream = fileService.getInputStreamResource(UUID.fromString(uuid));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(inputStream);
    }

    private GeneratorService getGeneratorService() {
        return applicationContext.getBean(GeneratorService.class);
    }
}
