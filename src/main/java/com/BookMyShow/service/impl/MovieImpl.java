package com.BookMyShow.service.impl;

import com.BookMyShow.dto.MovieDto;
import com.BookMyShow.entity.Movie;
import com.BookMyShow.exception.DependencyException;
import com.BookMyShow.exception.DuplicateRecordException;
import com.BookMyShow.repository.MovieRepository;
import com.BookMyShow.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public MovieDto createMovie(MovieDto movieDto) {

      Optional<Movie>optionalMovie=movieRepository.findByTitle(movieDto.getTitle());
      System.out.println(optionalMovie);
      if(optionalMovie.isPresent()){
          throw new DuplicateRecordException("movie already present with this "+movieDto.getTitle()+"title");
      }
      Movie movie=modelMapper.map(movieDto,Movie.class);
      Movie saveMovie=movieRepository.save(movie);
      return modelMapper.map(saveMovie,MovieDto.class);

    }

    @Override
    public MovieDto getMovie(String title) {
        Optional<Movie> optionalMovie=movieRepository.findByTitle(title);
        if(!optionalMovie.isPresent()){
            throw  new DependencyException("Movie is not present with this title"+title);
        }

        return modelMapper.map(optionalMovie,MovieDto.class);
    }
}
