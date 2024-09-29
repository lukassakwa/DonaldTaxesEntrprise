package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Naglowek {
    private KodFormularza kodFormularza;
    private byte wariantFormularza;
    private CelZlozenia celZlozenia;
    private NaglowekData data;
    private String kodUrzedu;
}
