package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PozycjeSzczegolowe {
    private String p23;
    private BigInteger p53;
}
