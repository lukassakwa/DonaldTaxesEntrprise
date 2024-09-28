package com.taxes.donaldtaxesentrprise.domain.gateway.out;

import java.io.OutputStream;
import java.util.UUID;

public interface FileRepository {
    void save(OutputStream outputStream, UUID uuid);
}
