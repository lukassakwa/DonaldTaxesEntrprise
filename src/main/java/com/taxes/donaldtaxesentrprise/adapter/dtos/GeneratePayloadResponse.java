package com.taxes.donaldtaxesentrprise.adapter.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneratePayloadResponse {
    private String uuid;
    private GenerationStatus status;
    private String message;
}
