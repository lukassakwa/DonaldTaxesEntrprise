package com.taxes.donaldtaxesentrprise.domain.xml;

import com.sun.codemodel.fmt.JStaticJavaFile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.URL;

public class MarshallerFactory {

    private MarshallerFactory() {
        throw new IllegalStateException("Utility class");
    }

    static Marshaller initMarshaller() throws JAXBException, SAXException {
        Marshaller marshaller = JAXBContext.newInstance(Deklaracja.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL resource = classloader.getResource("schema.xsd");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(resource);
        marshaller.setSchema(schema);
        return marshaller;
    }
}
