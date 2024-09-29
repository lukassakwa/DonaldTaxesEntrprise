package com.taxes.donaldtaxesentrprise.domain.teryt;

import org.springframework.stereotype.Service;

import java.io.InputStream;

public class CsvReaderUtils {

    static InputStream getCsvFile() {
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream("teryt.csv");
        if (resource == null) {
            throw new RuntimeException("Resource not found");
        }
        return resource;
    }

}
