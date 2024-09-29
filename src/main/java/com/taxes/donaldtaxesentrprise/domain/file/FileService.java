package com.taxes.donaldtaxesentrprise.domain.file;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private static final String ROOT_PATH = "C:/declarations";


    public InputStreamResource getInputStreamResource(UUID uuid) throws IOException {
        Path path = Paths.get(ROOT_PATH, uuid.toString());
        return new InputStreamResource(Files.newInputStream(getFile(path)));
    }


    public OutputStream getFileStream(UUID uuid) throws IOException {
        Path path = Paths.get(ROOT_PATH, uuid.toString());
        return Files.newOutputStream(getFile(path));
    }

    private Path getFile(Path path) {
        try {
            createDirectoryIfNotExists(path);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }


    private static void createDirectoryIfNotExists(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
