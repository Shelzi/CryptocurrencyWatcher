package com.shelzi.cryptocurrencywatcher.util;

public class PriceUsdConverter {
    public static String pennyToUsd(long pennyToConvert) {
        return String.format("%d.%02d", pennyToConvert / 100, pennyToConvert % 100);
    }

    public static long UsdToPenny(String usd) {
        return Long.parseLong(usd.substring(0, usd.indexOf(".")) + usd.substring(usd.indexOf(".") + 1));
    }
}