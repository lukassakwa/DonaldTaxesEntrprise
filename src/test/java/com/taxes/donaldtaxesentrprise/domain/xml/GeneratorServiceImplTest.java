package com.taxes.donaldtaxesentrprise.domain.xml;

import com.taxes.donaldtaxesentrprise.domain.file.FileService;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

class GeneratorServiceImplTest {

    @Test
    public void generateXml() throws JAXBException, SAXException {
        FileService fileService = new FileService();
        GeneratorServiceImpl generatorServiceImpl = new GeneratorServiceImpl(fileService);

        generatorServiceImpl.generateXml(null);
    }

}