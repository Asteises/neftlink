package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {

    private List<T> values;
    private PageInfo pageInfo;

}
