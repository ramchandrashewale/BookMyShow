package com.BookMyShow.service.impl;

import com.BookMyShow.dto.ScreenDto;
import com.BookMyShow.dto.TheaterDto;
import com.BookMyShow.entity.Movie;
import com.BookMyShow.entity.Screen;
import com.BookMyShow.entity.Theater;
import com.BookMyShow.exception.DuplicateRecordException;
import com.BookMyShow.exception.ResourceNotFoundException;
import com.BookMyShow.repository.MovieRepository;
import com.BookMyShow.repository.ScreenRepository;
import com.BookMyShow.repository.TheaterRepository;
import com.BookMyShow.service.TheaterAndScreenService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheaterImpl implements TheaterAndScreenService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public TheaterDto createTheater(TheaterDto theaterDto) {

        if(theaterRepository.existsByName(theaterDto.getName())){
            throw  new DuplicateRecordException("Theater with this name is already present");
        }
        Theater theater=modelMapper.map(theaterDto,Theater.class);
        Theater saveTheater=theaterRepository.save(theater);
        return modelMapper.map(saveTheater,TheaterDto.class);
    }

    @Override
    public TheaterDto getTheaterInfo(String name) {
        Optional<Theater> optionalTheaterDto=theaterRepository.findByName(name);

        if(!optionalTheaterDto.isPresent()){
            throw new EntityNotFoundException("Theater is not present this name"+name);
        }
        return modelMapper.map(optionalTheaterDto,TheaterDto.class);
    }

    @Override
    public ScreenDto addScreen(ScreenDto screenDto) {


       Optional<Theater> theater=theaterRepository.findByName(screenDto.getTheater().getName());
       if(!theater.isPresent()){
           throw  new EntityNotFoundException("No such theater found to add");
       }
        Screen screen=modelMapper.map(screenDto,Screen.class);
        screen.setTheater(theater.get());

       Screen saveScreen=screenRepository.save(screen);

        return modelMapper.map(saveScreen,ScreenDto.class);
    }


}
