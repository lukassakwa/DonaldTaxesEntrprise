package com.taxes.donaldtaxesentrprise.domain.teryt;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.taxes.donaldtaxesentrprise.domain.teryt.CsvReaderUtils.getCsvFile;

@Service
public class TerytService {
    private final TerytTrie trie;

    public TerytService() {
        trie = new TerytTrie();
        updateTrieWithValues();
    }

    public void validate(String[] path) throws Exception {
        if(!trie.exist(path)) {
            String message = String.join(", ", path);
            throw new Exception(String.format("teryt is invalid %s", message));
        };
    }

    private void updateTrieWithValues() {
        String file = getCsvFile().getFile();
        try (CSVReader reader = new CSVReader(new FileReader(file), ';')) {
            String[] line;
            while ((line = reader.readNext()) != null && line.length > 1) {
                trie.addPath(line);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            trie.clearCache();
        }
        System.out.println();
    }


}
