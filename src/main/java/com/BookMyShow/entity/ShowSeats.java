package com.BookMyShow.entity;

import com.BookMyShow.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ShowSeats")
public class ShowSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long show_seat_id;

    @Column(name = "SeatNumber",nullable = false)
    private String seatNumber;

    @Column(name = "is_booked", columnDefinition = "bit(1) default 0", nullable = false)
    private boolean booked;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false)
    private SeatType seatType;

    @Column(name = "rate", nullable = false)
    private int rate;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "booked_at")
//    private Date bookedAt;

    @ManyToOne
    @JoinColumn(name = "shows_show_id")
    private Shows shows;

    @ManyToOne
    private Booking booking;


}
