package com.taxes.donaldtaxesentrprise.domain.teryt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class TerytValidationServiceTest {
    @ParameterizedTest
    @MethodSource("citiesCombination")
    void test(List<String> path) {
        TerytValidationService instance = new TerytValidationService();

        Assertions.assertDoesNotThrow(() -> instance.validate(path.toArray(new String[0])));
    }


    private static Stream<Arguments> citiesCombination() {
        return Stream.of(
                Arguments.of(List.of("DOLNOŚLĄSKIE", "WROCŁAWSKI", "SOBÓTKA"), true),
                Arguments.of(List.of("*", "WROCŁAWSKI", "SOBÓTKA"), true),
                Arguments.of(List.of("DOLNOŚLĄSKIE", "*", "SOBÓTKA"), true),
                Arguments.of(List.of("DOLNOŚLĄSKIE", "WROCŁAWSKI", "*"), true),
                Arguments.of(List.of("DOLNOŚLSKIE", "WROCŁAWSKI", "*"), false),
                Arguments.of(List.of("DOLNOŚLSKIE", "WROŁAWSKI", "*"), false)
        );
    }
}