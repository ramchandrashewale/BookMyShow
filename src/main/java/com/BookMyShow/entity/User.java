package com.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "mobile")

    private String mobile;

    @Column(name = "email_id",nullable = false)
    private String email;

    @OneToMany(mappedBy
            = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;
}
