package com.lumen.api.v1.enums;

public enum CountriesEnum {

    BRAZIL("BRAZIL"),
    USA("USA"),
    CANADA("CANADA"),;

    CountriesEnum(String country) {
        this.country = country;
    }

    private final String country;

    public static boolean isCountryEqualsEnum(String country) {
        for (CountriesEnum countriesEnum : CountriesEnum.values()) {
            if (countriesEnum.name().equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }

    public String getValue() {
        return country;
    }
}
