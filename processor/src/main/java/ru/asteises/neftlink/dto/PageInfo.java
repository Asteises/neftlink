package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {

    private long elements;
    private long shift;
    private long pages;
    private long elementsCount;
    private long currentSize;

    public PageInfo(long elements, long shift, long elementsCount, long currentSize) {
        this.elements = elements;
        this.shift = shift;
        this.pages = elementsCount / elements;
        this.elementsCount = elementsCount;
        this.currentSize = currentSize;
    }
}
