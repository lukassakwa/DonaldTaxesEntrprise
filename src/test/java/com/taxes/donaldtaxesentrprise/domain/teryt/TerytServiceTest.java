package com.taxes.donaldtaxesentrprise.domain.teryt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TerytServiceTest {
    @ParameterizedTest
    @MethodSource("citiesCombination")
    void test(String[] path) {
        TerytService instance = new TerytService();

        boolean actual = instance.areTerytValuesValid(path);

        Assertions.assertTrue(actual);
    }


    private static Stream<Arguments> citiesCombination() {
        return Stream.of(
                Arguments.of(new String[]{"DOLNOŚLĄSKIE", "WROCŁAWSKI", "SOBÓTKA"}),
                Arguments.of(new String[]{"*", "WROCŁAWSKI", "SOBÓTKA"}),
                Arguments.of(new String[]{"DOLNOŚLĄSKIE", "*", "SOBÓTKA"}),
                Arguments.of(new String[]{"DOLNOŚLĄSKIE", "WROCŁAWSKI", "*"})
        );
    }
}