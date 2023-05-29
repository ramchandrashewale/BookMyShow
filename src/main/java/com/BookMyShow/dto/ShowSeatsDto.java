package com.BookMyShow.dto;

import com.BookMyShow.entity.Booking;
import com.BookMyShow.entity.Shows;
import com.BookMyShow.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatsDto {
    private String seatNumber;





    private boolean booked;




    private SeatType seatType;

    private int rate;





}
