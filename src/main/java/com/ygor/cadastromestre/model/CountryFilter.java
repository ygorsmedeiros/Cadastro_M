package com.ygor.cadastromestre.model;

import java.util.Locale;

public enum CountryFilter {
    BR("BR", "Brasil"),
    ARG("ARG", "Argentina"),
    CHL("CHL", "Chile");

    private final String code;
    private final String displayName;

    CountryFilter(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String code() {
        return code;
    }

    public String displayName() {
        return displayName;
    }

    public static CountryFilter fromCode(String code) {
        if (code == null) {
            return BR;
        }

        String normalized = code.toUpperCase(Locale.ROOT).trim();
        for (CountryFilter value : values()) {
            if (value.code.equals(normalized)) {
                return value;
            }
        }
        return BR;
    }
}
