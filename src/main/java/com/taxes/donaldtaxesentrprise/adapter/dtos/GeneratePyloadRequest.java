package com.taxes.donaldtaxesentrprise.adapter.dtos;

import com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest.Naglowek;
import com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest.Podmiot;
import com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest.PozycjeSzczegolowe;
import com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest.Zalacznik;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneratePyloadRequest {
    private Naglowek naglowek;
    private Podmiot podmiot;
    private PozycjeSzczegolowe pozycjeSzczegolowe;
    private BigDecimal pouczenia;
    private Zalacznik zalacznik;
}
