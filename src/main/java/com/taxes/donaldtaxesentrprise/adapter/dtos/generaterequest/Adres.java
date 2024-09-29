package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import com.taxes.donaldtaxesentrprise.domain.xml.TKodKraju;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adres {
    private String kodKraju;
    private String wojewodztwo;
    private String powiat;
    private String gmina;
    private String ulica;
    private String nrDomu;
    private String nrLokalu;
    private String miejscowosc;
    private String kodPocztowy;
}
