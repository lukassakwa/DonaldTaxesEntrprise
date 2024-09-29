package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaglowekData {
    private LocalDate value;
    private String poz;
}
