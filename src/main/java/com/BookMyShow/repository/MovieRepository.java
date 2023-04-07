package com.BookMyShow.repository;

import com.BookMyShow.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Integer > {
     Optional<Movie> findByTitle(String title);
}
