package com.taxes.donaldtaxesentrprise.domain.teryt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class TerytServiceTest {
    @ParameterizedTest
    @MethodSource("citiesCombination")
    void test(List<String> path) {
        TerytService instance = new TerytService();

        boolean actual = instance.areTerytValuesValid(path.toArray(new String[0]));

        Assertions.assertTrue(actual);
    }


    private static Stream<Arguments> citiesCombination() {
        return Stream.of(
                Arguments.of(List.of("DOLNOŚLĄSKIE", "WROCŁAWSKI", "SOBÓTKA")),
                Arguments.of(List.of("*", "WROCŁAWSKI", "SOBÓTKA")),
                Arguments.of(List.of("DOLNOŚLĄSKIE", "*", "SOBÓTKA")),
                Arguments.of(List.of("DOLNOŚLĄSKIE", "WROCŁAWSKI", "*"))
        );
    }
}