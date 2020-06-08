package com.github.homework.theme.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThemeParser {
    public static final String DATA_SEPARATOR = ",";

    public static List<String> parse(String themeName) {
        return Arrays.stream(themeName.split(DATA_SEPARATOR))
                .map(String::trim)
                .filter(theme -> theme.length() > 0)
                .collect(Collectors.toList());
    }
}