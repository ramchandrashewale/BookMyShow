package com.BookMyShow.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private int number;
    private int records;

    private long totalRecords;
    private int totalPages;

    private List<T> data;
}
