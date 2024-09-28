package com.taxes.donaldtaxesentrprise.domain.gateway.in;

import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePayloadResponse;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePyloadRequest;

public interface GeneratorService {
    GeneratePayloadResponse generateXml(GeneratePyloadRequest request);
}
