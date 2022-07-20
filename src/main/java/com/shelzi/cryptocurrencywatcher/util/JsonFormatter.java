package com.shelzi.cryptocurrencywatcher.util;

public class JsonFormatter {
    public static String formatJsonObject(String input) {
        return input.replaceAll("[\\[\\]]", "");
    }

    public static String formatJsonArray(String input) {
        return input.substring(input.indexOf("["), input.indexOf("]") + 1);
    }
}
