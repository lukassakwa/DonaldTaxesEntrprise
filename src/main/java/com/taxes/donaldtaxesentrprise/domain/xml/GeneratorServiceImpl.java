package com.taxes.donaldtaxesentrprise.domain.xml;

import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePayloadResponse;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePyloadRequest;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GenerationStatus;
import com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest.Adres;
import com.taxes.donaldtaxesentrprise.adapter.dtos.generaterequest.Naglowek;
import com.taxes.donaldtaxesentrprise.domain.file.FileService;
import com.taxes.donaldtaxesentrprise.domain.gateway.in.GeneratorService;
import com.taxes.donaldtaxesentrprise.domain.teryt.TerytValidationService;
import com.taxes.donaldtaxesentrprise.domain.teryt.UrzadSkarbowyValidationService;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

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
    private final TerytValidationService terytValidationService;
    private final UrzadSkarbowyValidationService urzadSkarbowyValidationService;

    public GeneratorServiceImpl(FileService fileService,
                                TerytValidationService terytValidationService,
                                UrzadSkarbowyValidationService urzadSkarbowyValidationService
    ) throws JAXBException, SAXException {
        this.fileService = fileService;
        this.terytValidationService = terytValidationService;
        this.urzadSkarbowyValidationService = urzadSkarbowyValidationService;
        objectFactory = new ObjectFactory();
        marshaller = MarshallerFactory.initMarshaller();
    }

    public GeneratePayloadResponse generateXml(GeneratePyloadRequest request) throws Exception {
        final UUID uuid = UUID.randomUUID();

        technicalValidation(request);

        try (OutputStream outputStream = fileService.getFileStream(uuid)) {

            //TODO:: mapstruct and map all fields

            Deklaracja deklaracja = objectFactory.createDeklaracja();
            deklaracja.setNaglowek(GeneratorMapperUtils.createTNaglowek(request.getNaglowek()));
            deklaracja.setPodmiot1(GeneratorMapperUtils.createPodmiot1(request.getPodmiot()));
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
        Adres adres = request.getPodmiot().getAdres();
        terytValidationService.validate(
                new String[]{adres.getWojewodztwo(), adres.getPowiat(), adres.getGmina()}
        );
        urzadSkarbowyValidationService.validate(request.getNaglowek().getKodUrzedu());
    }
}
