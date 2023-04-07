package com.BookMyShow.controller;

import com.BookMyShow.dto.ScreenDto;
import com.BookMyShow.dto.TheaterDto;
import com.BookMyShow.service.TheaterAndScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterAndScreenService theaterAndScreenService;



    @PostMapping
    public ResponseEntity<TheaterDto> createTheater(@RequestBody TheaterDto theaterDto){
        TheaterDto theaterDto1=theaterAndScreenService.createTheater(theaterDto);
        return new ResponseEntity<>(theaterDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TheaterDto> getTheater(@RequestParam(value = "name") String name){
        TheaterDto theaterDto=theaterAndScreenService.getTheaterInfo(name);
        return new ResponseEntity<>(theaterDto,HttpStatus.OK);
    }

    @PostMapping("/screen")
    public ResponseEntity<ScreenDto> createScreen(@RequestBody ScreenDto screenDto){
      ScreenDto screenDto1=theaterAndScreenService.addScreen(screenDto);
        return new ResponseEntity<>(screenDto1, HttpStatus.CREATED);
    }
//    @GetMapping("/screenID")
//    public ResponseEntity<ScreenDto> getScreenById(@RequestParam(value = "id")Long id){
//        ScreenDto screenDto=theaterAndScreenService.getScreenInfo(id);
//        return new ResponseEntity<>(screenDto,HttpStatus.OK);
//    }

}
