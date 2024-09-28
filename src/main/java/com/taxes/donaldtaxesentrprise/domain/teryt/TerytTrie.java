package com.taxes.donaldtaxesentrprise.domain.teryt;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class TerytTrie {
    private final Trie root = new Trie();
    private final HashMap<String, String> cache = new HashMap<>();


    void addPath(String[] line) {
        String key = createKey(line);
        cache.put(key, line[4].toUpperCase());
        Trie trie = root;
        for (int i = 2; i <= key.length(); i+=2) {
            String locutionKey = key.substring(0, i);
            String locution = cache.get(locutionKey);
            if (!trie.child.containsKey(locution)) {
                trie.child.put(locution, new Trie());
            }
            trie = trie.child.get(locution);
        }
    }

    public boolean exist(String[] line) {
        prepareListBeforeValidation(line);
        return exist(line, root, 0);
    }

    private boolean exist(String[] path, Trie trie, int j) {
        for (int i = j; i < path.length; i++) {
            if ("*".equals(path[i])) {
                for (Trie child : trie.child.values()) {
                    if (exist(path, child, i+1)) {
                        return true;
                    }
                }
            }
            if (!trie.child.containsKey(path[i])) {
                return false;
            }
            trie = trie.child.get(path[i]);
        }
        return true;
    }

    private void prepareListBeforeValidation(String[] path) {
        for (int i = 0; i < path.length; i++) {
            if (StringUtils.isEmpty(path[i])) {
                path[i] = "*";
            }
            path[i] = path[i].toUpperCase();
        }
    }

    void clearCache() {
        cache.clear();
    }

    private static String createKey(String[] line) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i <= 2; i++) {
            if (StringUtils.isEmpty(line[i])) {
                continue;
            }
            key.append(line[i]);
        }
        return key.toString();
    }


    private static class Trie {
        HashMap<String, Trie> child = new HashMap<>();
    }
}
