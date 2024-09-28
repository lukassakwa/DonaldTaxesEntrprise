package com.taxes.donaldtaxesentrprise.domain.teryt;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

import static com.taxes.donaldtaxesentrprise.domain.teryt.CsvReaderUtils.getCsvFile;

@Service
public class TerytService {
    private final TerytTrie trie;

    public TerytService() {
        trie = new TerytTrie();
        updateTrieWithValues();
    }

    public boolean areTerytValuesValid(String[] path) {
        return trie.exist(path);
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
