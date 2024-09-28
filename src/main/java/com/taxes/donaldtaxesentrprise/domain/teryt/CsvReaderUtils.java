package com.taxes.donaldtaxesentrprise.domain.teryt;

import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class CsvReaderUtils {

    static URL getCsvFile() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL resource = classloader.getResource("TERC_Adresowy_2024-09-28.csv");
        if (resource == null) {
            throw new RuntimeException("Resource not found");
        }
        return resource;
    }

}
