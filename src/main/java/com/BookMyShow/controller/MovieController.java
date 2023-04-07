package com.BookMyShow.controller;

import com.BookMyShow.dto.MovieDto;
import com.BookMyShow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto){
       MovieDto movieDto1= movieService.createMovie(movieDto);
      return new  ResponseEntity<>(movieDto1, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<MovieDto> getMovie(@RequestParam(value = "title",required = true)String title){
        MovieDto movieDto=movieService.getMovie(title);
        return  new ResponseEntity<>(movieDto,HttpStatus.OK);
    }

}
