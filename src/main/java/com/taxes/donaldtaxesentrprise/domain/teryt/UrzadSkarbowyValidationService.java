package com.taxes.donaldtaxesentrprise.domain.teryt;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrzadSkarbowyValidationService {

    private final HashSet<String> codes = new HashSet<>();

    UrzadSkarbowyValidationService() {
        String regex = "xsd:enumeration\\s+value=\"(\\d+)\"";
        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceUtils.getResource("skarbowy.xsd")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // Extract and store the value
                    String value = matcher.group(1);
                    codes.add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(codes);
    }

    public void validate(String code) throws Exception {
        if(codes.contains(code)) {
            return;
        }
        throw new Exception(String.format("Code is invalid %s", code));
    }

}
