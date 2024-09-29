package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KodFormularza {
    protected String kodSystemowy;
    protected String kodPodatku;
    protected String rodzajZobowiazania;
    protected String wersjaSchemy;
}
