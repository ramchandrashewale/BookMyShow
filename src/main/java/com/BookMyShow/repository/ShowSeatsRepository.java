package com.BookMyShow.repository;

import com.BookMyShow.entity.ShowSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatsRepository extends JpaRepository<ShowSeats,Long> {
}
