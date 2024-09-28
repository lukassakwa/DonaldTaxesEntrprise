package com.taxes.donaldtaxesentrprise.adapter;

import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePayloadResponse;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePyloadRequest;
import com.taxes.donaldtaxesentrprise.domain.gateway.in.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Provider;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class XmlController {
    private final Provider<GeneratorService> generatorServiceProvider;

    @PostMapping("/generate")
    ResponseEntity<GeneratePayloadResponse> generate(GeneratePyloadRequest request) {
        GeneratePayloadResponse payload = getGeneratorService().generateXml(request);
        return ResponseEntity.ok(payload);
    }

    private GeneratorService getGeneratorService() {
        return generatorServiceProvider.get();
    }
}
