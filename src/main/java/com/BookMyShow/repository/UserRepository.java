package com.BookMyShow.repository;

import com.BookMyShow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {



    boolean existsByMobile(String mobile);


}
