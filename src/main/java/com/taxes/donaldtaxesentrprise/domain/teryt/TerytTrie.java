package com.taxes.donaldtaxesentrprise.domain.teryt;

import java.util.HashMap;

public class TerytTrie {
    private final HashMap<String, Trie> map = new HashMap<>();




    private static class Trie {
        HashMap<String, Trie> child = new HashMap<>();
        boolean end;
    }
}
