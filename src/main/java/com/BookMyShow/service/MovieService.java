package com.BookMyShow.service;

import com.BookMyShow.dto.MovieDto;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto);

    MovieDto getMovie(String name);

}
