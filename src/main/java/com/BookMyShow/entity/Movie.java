package com.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;
    @Column(name = "Title",nullable = false)
    private String title;
    @Column(name = "Director",nullable = false)
    private String director;

    @Column(name="Language",nullable = false)
    private String language;

    @Column(name = "Description",nullable = false)
    private String description;

    @Column(name = "Duration",nullable = false)
    private Long duration;

    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Shows> shows =new ArrayList<>();



}
