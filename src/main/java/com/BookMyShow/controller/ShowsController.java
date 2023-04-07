package com.BookMyShow.controller;

import com.BookMyShow.dto.PageResponse;
import com.BookMyShow.dto.ShowDto;
import com.BookMyShow.service.ShowService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowsController {

    @Autowired
    private ShowService service;

    @PostMapping
    public ResponseEntity<ShowDto> createShow(@RequestBody ShowDto showDto){
        ShowDto showDto1=service.createShow(showDto);
        return new ResponseEntity<>(showDto1, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ShowDto>> search(@RequestParam(value = "pageNo",defaultValue = "1",required = false)int pageNo,
                                       @RequestParam(value = "limit",defaultValue = "1",required = false) int limit,
                                       @RequestParam(value = "movieName",required = false)String movieName,
                                       @RequestParam(value = "city",required = false)String city,
                                       @RequestParam(value = "theaterName",required = false)String theaterName,
                                       @RequestParam(value = "showDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate showDate,
                                       @RequestParam(value = "showTime",required = false) @DateTimeFormat(pattern = "HH:mm:ss") LocalTime showTime){
        List<ShowDto> showDtoList=service.searchShow(movieName,city,theaterName,showDate,showTime,pageNo,limit);
        return new ResponseEntity<>(showDtoList,HttpStatus.OK);

    }
}
