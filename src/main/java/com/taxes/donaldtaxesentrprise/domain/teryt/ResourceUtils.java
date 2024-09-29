package com.taxes.donaldtaxesentrprise.domain.teryt;

import org.springframework.stereotype.Service;

import java.io.InputStream;

public class ResourceUtils {

    static InputStream getResource(String filename) {
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        if (resource == null) {
            throw new RuntimeException("Resource not found");
        }
        return resource;
    }

}
