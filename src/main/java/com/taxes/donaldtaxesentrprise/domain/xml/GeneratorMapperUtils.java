package com.taxes.donaldtaxesentrprise.domain.xml;

import com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class GeneratorMapperUtils {

    private static ObjectFactory objectFactory = new ObjectFactory();

    static TNaglowek createTNaglowek(Naglowek req) {
        TNaglowek  naglowek = objectFactory.createTNaglowek();
        naglowek.setKodFormularza(GeneratorMapperUtils.createKodFormularza(req.getKodFormularza()));
        naglowek.setData(GeneratorMapperUtils.createNaglowekData(req.getData()));
        naglowek.setWariantFormularza((byte) 0);
        naglowek.setKodUrzedu(req.getKodUrzedu());
        naglowek.setCelZlozenia(GeneratorMapperUtils.createCelZlozenia(req.getCelZlozenia()));
        return naglowek;
    }

    private static TNaglowek.KodFormularza createKodFormularza(KodFormularza kodFormularza) {
        TNaglowek.KodFormularza tkodFormularza = objectFactory.createTNaglowekKodFormularza();
        tkodFormularza.setValue(TKodFormularza.PCC_3);
        tkodFormularza.setKodPodatku(kodFormularza.getKodPodatku());
        tkodFormularza.setKodSystemowy(kodFormularza.getKodSystemowy());
        tkodFormularza.setWersjaSchemy(kodFormularza.getWersjaSchemy());
        tkodFormularza.setRodzajZobowiazania(kodFormularza.getRodzajZobowiazania());
        return tkodFormularza;
    }

    private static TNaglowek.Data createNaglowekData(NaglowekData data) {
        TNaglowek.Data naglowekData = objectFactory.createTNaglowekData();
        naglowekData.setValue(toXmlGregorianCalendar(data.getValue()));
        naglowekData.setPoz(data.getPoz());
        return naglowekData;
    }

    private static TNaglowek.CelZlozenia createCelZlozenia(CelZlozenia celZlozenia) {
        TNaglowek.CelZlozenia cel = objectFactory.createTNaglowekCelZlozenia();
        cel.setValue(celZlozenia.getValue());
        cel.setPoz(celZlozenia.getPoz());
        return cel;
    }

    public static Deklaracja.Podmiot1 createPodmiot1(Podmiot podmiot) {
        Deklaracja.Podmiot1 podmiot1 = objectFactory.createDeklaracjaPodmiot1();
        //temp always osoba nie fizyczna
        podmiot1.setOsobaNiefizyczna(createOsobaNiefizyczna(podmiot.getOsobaNieFizyczna()));
        podmiot1.setAdresZamieszkaniaSiedziby(createAdresZamieszkania(podmiot.getAdres()));
        podmiot1.setRola(podmiot.getRola());
        return podmiot1;
    }

    private static Deklaracja.Podmiot1.AdresZamieszkaniaSiedziby createAdresZamieszkania(Adres adres) {
        TAdresPolski adresPolski = objectFactory.createTAdresPolski();
        adresPolski.setWojewodztwo(adres.getWojewodztwo());
        adresPolski.setPowiat(adres.getPowiat());
        adresPolski.setGmina(adres.getGmina());

        Deklaracja.Podmiot1.AdresZamieszkaniaSiedziby adresZamieszkaniaSiedziby = objectFactory.createDeklaracjaPodmiot1AdresZamieszkaniaSiedziby();
        adresZamieszkaniaSiedziby.setAdresPol(adresPolski);
        return adresZamieszkaniaSiedziby;
    }

    private static TIdentyfikatorOsobyNiefizycznejLocal createOsobaNiefizyczna(OsobaNieFizyczna osobaNieFizyczna) {
        TIdentyfikatorOsobyNiefizycznejLocal osobyNiefizycznejLocal = objectFactory.createTIdentyfikatorOsobyNiefizycznejLocal();
        osobyNiefizycznejLocal.setNIP(osobaNieFizyczna.getNip());
        osobyNiefizycznejLocal.setPelnaNazwa(osobaNieFizyczna.getPelnaNazwa());
        osobyNiefizycznejLocal.setSkroconaNazwa(osobaNieFizyczna.getSkroconaNazwa());
        return osobyNiefizycznejLocal;
    }

    private static XMLGregorianCalendar toXmlGregorianCalendar(LocalDate localDate) {
        try {
            // Convert LocalDate to GregorianCalendar
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));

            // Create an XMLGregorianCalendar from GregorianCalendar
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
