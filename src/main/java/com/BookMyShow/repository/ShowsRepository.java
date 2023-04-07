package com.BookMyShow.repository;

import com.BookMyShow.entity.Shows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowsRepository extends JpaRepository<Shows,Integer> {


   // Page<Shows> findAll(Specification<Shows> specifications, Pageable p);

    List<Shows> findAll(Specification<Shows> specifications);

    Page<Shows> findAll(Specification<Shows> specifications, Pageable p);
}
