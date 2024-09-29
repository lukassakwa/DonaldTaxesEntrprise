package com.taxes.donaldtaxesentrprise.domain.teryt;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.*;

import static com.taxes.donaldtaxesentrprise.domain.teryt.CsvReaderUtils.getCsvFile;

@Service
public class TerytService {
    private final TerytTrie trie;
    private boolean initialized;

    public TerytService() {
        trie = new TerytTrie();
        initialized = false;
    }

    public void validate(String[] path) throws Exception {
        if (!initialized) {
            updateTrieWithValues();
        }
        if(trie.exist(path)) {
            return;
        }
        String message = String.join(", ", path);
        throw new Exception(String.format("teryt is invalid %s", message));
    }

    private void updateTrieWithValues() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(getCsvFile()), ';')) {
            String[] line;
            while ((line = reader.readNext()) != null && line.length > 1) {
                trie.addPath(line);
            }
            initialized = true;
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            trie.clearCache();
        }
    }


}
