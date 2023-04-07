package com.BookMyShow.service;

import com.BookMyShow.dto.BookTicketRequestDto;
import com.BookMyShow.dto.BookingDto;

public interface BookService {
    BookingDto booking(BookTicketRequestDto bookTicketRequestDto);

    BookingDto getBooking(Long id);
}
