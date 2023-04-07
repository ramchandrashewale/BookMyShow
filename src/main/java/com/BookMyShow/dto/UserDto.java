package com.BookMyShow.dto;

import com.BookMyShow.entity.Booking;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {


    private String name;



    private String mobile;


    private String email;

    private List<Booking> bookings;


}
