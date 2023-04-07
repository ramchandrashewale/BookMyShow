package com.BookMyShow.service;

import com.BookMyShow.dto.ShowDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowService {

    ShowDto createShow(ShowDto showDto);

    List<ShowDto> searchShow(String movieName, String city, String theaterName, LocalDate showDate, LocalTime showTime, int pageNo, int limit);
}
