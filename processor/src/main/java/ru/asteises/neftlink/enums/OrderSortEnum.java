package ru.asteises.neftlink.enums;

public enum OrderSortEnum {

    COST("cost"),
    GAS_TYPE("gasType"),
    BASE_NAME("baseName");

    private final String text;

    OrderSortEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
