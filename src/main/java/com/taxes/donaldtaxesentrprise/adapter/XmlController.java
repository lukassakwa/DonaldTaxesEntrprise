package com.taxes.donaldtaxesentrprise.adapter;

import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePayloadResponse;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePyloadRequest;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GenerationStatus;
import com.taxes.donaldtaxesentrprise.domain.gateway.in.GeneratorService;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Provider;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class XmlController {
    private final Provider<GeneratorService> generatorServiceProvider;

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

    private GeneratorService getGeneratorService() {
        return generatorServiceProvider.get();
    }
}
