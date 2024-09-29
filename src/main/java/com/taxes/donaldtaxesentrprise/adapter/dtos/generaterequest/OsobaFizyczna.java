package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.datatype.XMLGregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OsobaFizyczna {
    private String nip;
    private String pesel;
    private String imiePierwsze;
    private String nazwisko;
    private XMLGregorianCalendar dataUrodzenia;
    private String imieOjca;
    private String imieMatki;
}
