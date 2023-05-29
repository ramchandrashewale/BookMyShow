package com.BookMyShow.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screen_id;

    private String name;


    @ManyToOne
    @JsonBackReference
    private Theater theater;

    private Integer capacity;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Shows> shows=new ArrayList<>();

}
