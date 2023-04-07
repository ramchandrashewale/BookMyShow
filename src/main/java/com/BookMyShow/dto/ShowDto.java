package com.BookMyShow.dto;

import com.BookMyShow.entity.Movie;
import com.BookMyShow.entity.Screen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {



    private LocalDate showDate;


    private LocalTime showTime;

    private MovieDto movie;


    private ScreenDto screen;


    private List<ShowSeatsDto> seats=new ArrayList<>();

}
