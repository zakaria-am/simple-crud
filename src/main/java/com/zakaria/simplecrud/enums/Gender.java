package com.zakaria.simplecrud.enums;

public enum Gender implements EnumParam{
    MALE("M",Gender.class+".MALE"),
    FEMALE("F",Gender.class+".FEMALE")
    ;

    public String value;
    public String text;
    private Gender(String value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getValue() {
        return value;
    }
}
