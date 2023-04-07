package com.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Bookings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long booking_id;

//   @CreatedDate
//   @Temporal(TemporalType.TIMESTAMP)
//   @Column(name = "booked_at", nullable = false)
//   private Date bookedAt;

   @Column(name = "amount")
   private double amount;

   @Column(name = "alloted_seats")
   private String allottedSeats;

   @ManyToOne
   private User user;

   @ManyToOne
   private Shows shows;

   @JoinColumn(name = "shows")
   @OneToMany
   @JsonIgnore
   private List<ShowSeats> showSeats;
}
