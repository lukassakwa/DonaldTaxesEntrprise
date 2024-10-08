package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Podmiot {
    private OsobaFizyczna osobaFizyczna;
    private OsobaNieFizyczna osobaNieFizyczna;
    private Adres adres;
    private String rola;
}
