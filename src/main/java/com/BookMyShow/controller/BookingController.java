package com.BookMyShow.controller;

import com.BookMyShow.dto.BookTicketRequestDto;
import com.BookMyShow.dto.BookingDto;
import com.BookMyShow.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookingDto> bookingTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto){
        BookingDto bookingDto=bookService.booking(bookTicketRequestDto);
        return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
    }
}
