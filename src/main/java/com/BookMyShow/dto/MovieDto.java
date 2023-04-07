package com.BookMyShow.dto;

import com.BookMyShow.entity.Shows;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private String title;

    private String director;


    private String description;


    private Long duration;

    private String language;


    @JsonIgnore
    private List<Shows> shows =new ArrayList<>();

}
