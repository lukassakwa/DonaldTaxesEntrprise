package com.taxes.donaldtaxesentrprise.domain.xml;

import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePayloadResponse;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GeneratePyloadRequest;
import com.taxes.donaldtaxesentrprise.adapter.dtos.GenerationStatus;
import com.taxes.donaldtaxesentrprise.domain.file.FileService;
import com.taxes.donaldtaxesentrprise.domain.gateway.in.GeneratorService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public GeneratorServiceImpl(FileService fileService) throws JAXBException, SAXException {
        this.fileService = fileService;
        objectFactory = new ObjectFactory();
        marshaller = MarshallerFactory.initMarshaller();
    }

    public GeneratePayloadResponse generateXml(GeneratePyloadRequest request) {
        final UUID uuid = UUID.randomUUID();
        try (OutputStream outputStream = fileService.getFileStream(uuid)) {

            Deklaracja deklaracja = objectFactory.createDeklaracja();


            deklaracja.setNaglowek(new TNaglowek());
            deklaracja.setPodmiot1(new Deklaracja.Podmiot1());
            deklaracja.setPozycjeSzczegolowe(new Deklaracja.PozycjeSzczegolowe());
            deklaracja.setPouczenia(BigDecimal.ZERO);

            marshaller.marshal(deklaracja, outputStream);

        } catch (IOException | JAXBException e) {
            return GeneratePayloadResponse.builder()
                    .status(GenerationStatus.FAILURE)
                    .message(e.toString())
                    .build();
        }

        return GeneratePayloadResponse.builder()
                .message(uuid.toString())
                .status(GenerationStatus.SUCCESS)
                .build();
    }

}
