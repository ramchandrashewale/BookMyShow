package com.BookMyShow.service;

import com.BookMyShow.dto.ScreenDto;
import com.BookMyShow.dto.TheaterDto;
import com.BookMyShow.entity.Theater;

public interface TheaterAndScreenService {
    TheaterDto createTheater(TheaterDto theaterDto);

    TheaterDto getTheaterInfo(String name);

    ScreenDto addScreen(ScreenDto screenDto);

   // ScreenDto getScreenInfo(Long id);
}
