package com.newsweaver.lottery.util;

/**
 * Created by gary on 08/10/2016.
 */
public class StringUtils {
    private static final String SPACE = " ";

    public static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ), SPACE);
    }
}
