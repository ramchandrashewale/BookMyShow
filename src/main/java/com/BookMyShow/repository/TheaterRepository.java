package com.BookMyShow.repository;

import com.BookMyShow.dto.TheaterDto;
import com.BookMyShow.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater,Long> {
  

    

    boolean existsByName(String name);


    Optional<Theater> findByName(String name);
}
