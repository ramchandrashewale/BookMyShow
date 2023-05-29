package com.BookMyShow.dto;

import com.BookMyShow.entity.Screen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {



    private String name;


    private String city;


    private String address;


    @
    JsonIgnore
    private List<ScreenDto> screen=new ArrayList<>();


}
