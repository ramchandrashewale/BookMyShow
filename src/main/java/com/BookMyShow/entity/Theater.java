package com.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theater_id;
    @Column(name = "Name",nullable = false)
    private String name;

    @Column(name = "City",nullable = false)
    private String city;

    @Column(name = "Address",nullable = false)
    private String address;


    @OneToMany(mappedBy = "theater",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Screen> screen=new ArrayList<>();

}
