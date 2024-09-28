package com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest;

import com.taxes.donaldtaxesentrprise.domain.xml.TKodFormularzaZU;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Zalacznik {
    private TKodFormularzaZU value;
    private String kodSystemowy;
    private String wersjaSchemy;
    private byte wariantFormularza;
    protected String p13;
}
