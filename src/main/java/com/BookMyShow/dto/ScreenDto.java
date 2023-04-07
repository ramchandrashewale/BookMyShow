package com.BookMyShow.dto;

import com.BookMyShow.entity.Movie;
import com.BookMyShow.entity.Theater;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScreenDto {
    private Long screen_id;

    private String name;



    private TheaterDto theater;

    private Integer capacity;



}
