package com.taxes.donaldtaxesentrprise.domain.teryt;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.*;

import static com.taxes.donaldtaxesentrprise.domain.teryt.ResourceUtils.getResource;

@Service
public class TerytValidationService {
    private final TerytTrie trie;

    public TerytValidationService() {
        trie = new TerytTrie();
        updateTrieWithValues();
    }

    public void validate(String[] path) throws Exception {
        if(trie.exist(path)) {
            return;
        }
        String message = String.join(", ", path);
        throw new Exception(String.format("teryt is invalid %s", message));
    }

    private void updateTrieWithValues() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(getResource("teryt.csv")), ';')) {
            String[] line;
            while ((line = reader.readNext()) != null && line.length > 1) {
                trie.addPath(line);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            trie.clearCache();
        }
    }


}
