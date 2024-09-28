package com.taxes.donaldtaxesentrprise.domain.xml;

import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePayloadResponse;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePyloadRequest;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GenerationStatus;
import com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest.Naglowek;
import com.taxes.donaldtaxesentrprise.domain.file.FileService;
import com.taxes.donaldtaxesentrprise.domain.gateway.in.GeneratorService;
import com.taxes.donaldtaxesentrprise.domain.teryt.TerytService;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Service
@Scope(SCOPE_PROTOTYPE)
public class GeneratorServiceImpl implements GeneratorService {

    private final FileService fileService;
    private final ObjectFactory objectFactory;
    private final Marshaller marshaller;
    private final TerytService terytService;

    public GeneratorServiceImpl(FileService fileService, TerytService terytService) throws JAXBException, SAXException {
        this.fileService = fileService;
        this.terytService = terytService;
        objectFactory = new ObjectFactory();
        marshaller = MarshallerFactory.initMarshaller();
    }

    public GeneratePayloadResponse generateXml(GeneratePyloadRequest request) throws Exception {
        final UUID uuid = UUID.randomUUID();

        technicalValidation(request);

        try (OutputStream outputStream = fileService.getFileStream(uuid)) {

            Deklaracja deklaracja = objectFactory.createDeklaracja();
            deklaracja.setNaglowek(createTNaglowek(request.getNaglowek()));
            deklaracja.setPodmiot1(new Deklaracja.Podmiot1());
            deklaracja.setPozycjeSzczegolowe(new Deklaracja.PozycjeSzczegolowe());
            deklaracja.setPouczenia(BigDecimal.ZERO);

            marshaller.marshal(deklaracja, outputStream);
        }

        return GeneratePayloadResponse.builder()
                .message(uuid.toString())
                .status(GenerationStatus.SUCCESS)
                .build();
    }

    private void technicalValidation(GeneratePyloadRequest request) throws Exception {
        //TODO:: add variables
        String[] paths = new String[]{};
        terytService.validate(paths);
    }


    private TNaglowek createTNaglowek(Naglowek req) {
        TNaglowek  naglowek = objectFactory.createTNaglowek();
        TNaglowek.KodFormularza kodFormularza = new TNaglowek.KodFormularza();
        kodFormularza.setValue(TKodFormularza.PCC_3);
        kodFormularza.setKodPodatku(req.getKodPodatku());
        kodFormularza.setKodSystemowy(req.getKodSystemowy());
        kodFormularza.setWersjaSchemy(req.getWersjaSchemy());
        kodFormularza.setRodzajZobowiazania(req.getRodzajZobowiazania());

        naglowek.setKodFormularza(kodFormularza);
        //naglowek.
        //naglowek.setWariantFormularza(req.getWariantFormularza());
        return naglowek;
    }

}
