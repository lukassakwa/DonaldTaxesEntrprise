package com.taxes.donaldtaxesentrprise.domain.xml;

import com.taxes.donaldtaxesentrprise.domain.file.FileService;
import com.taxes.donaldtaxesentrprise.domain.teryt.TerytService;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

class GeneratorServiceImplTest {

    @Test
    public void generateXml() throws Exception {
        FileService fileService = new FileService();
        TerytService terytService = new TerytService();
        GeneratorServiceImpl generatorServiceImpl = new GeneratorServiceImpl(fileService, terytService);

        generatorServiceImpl.generateXml(null);
    }

}