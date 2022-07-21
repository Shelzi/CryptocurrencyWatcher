package com.shelzi.cryptocurrencywatcher.util;

public class JsonFormatter {
    public static final String FORMAT_JSON_OBJECT_REGEX = "[\\[\\]]";

    public static String formatJsonObject(String input) {
        return input.replaceAll(FORMAT_JSON_OBJECT_REGEX, "");
    }
}
