package com.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Shows")
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int show_id;

    @Column(name = "Date",nullable = false)
    private LocalDate showDate;

    @Column(name = "Time",nullable = false)
    private LocalTime showTime;

    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "shows",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking>  bookings;

    @OneToMany(mappedBy = "shows",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowSeats> seats = new ArrayList<>();

}
