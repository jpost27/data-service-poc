package com.jp.codegen.util;

import com.google.common.base.CaseFormat;

public class CaseUtil {

    public static CaseFormat detect(String input) {
        if (input.contains("_")) {
            // Underscore
            if (Character.isUpperCase(input.charAt(0))) {
                return CaseFormat.UPPER_UNDERSCORE;
            } else {
                return CaseFormat.LOWER_UNDERSCORE;
            }
        } else if (input.contains("-")) {
            // Hyphen
            return CaseFormat.LOWER_HYPHEN;
        } else {
            // Underscore
            if (Character.isUpperCase(input.charAt(0))) {
                return CaseFormat.UPPER_CAMEL;
            } else {
                return CaseFormat.LOWER_CAMEL;
            }
        }
    }

    public static String convert(String input, CaseFormat targetFormat) {
        return detect(input).to(targetFormat, input);
    }
}
