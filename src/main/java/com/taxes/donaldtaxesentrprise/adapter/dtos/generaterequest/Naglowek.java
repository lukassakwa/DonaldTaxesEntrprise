package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Naglowek {
    private String kodSystemowy;
    private String kodPodatku;
    private String rodzajZobowiazania;
    private byte wariantFormularza;
    private byte value;
    private String poz;
    private String wersjaSchemy;
}
