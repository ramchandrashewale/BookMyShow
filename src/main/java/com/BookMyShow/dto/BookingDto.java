package com.BookMyShow.dto;

import com.BookMyShow.entity.ShowSeats;
import com.BookMyShow.entity.Shows;
import com.BookMyShow.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
  //  private Date bookedAt;

    private double amount;


    private String allottedSeats;


    private User user;


    private Shows shows;


    private List<ShowSeats> showSeats;
}
