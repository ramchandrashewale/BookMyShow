package com.BookMyShow.service.impl;

import com.BookMyShow.dto.BookTicketRequestDto;
import com.BookMyShow.dto.BookingDto;
import com.BookMyShow.entity.Booking;
import com.BookMyShow.entity.ShowSeats;
import com.BookMyShow.entity.Shows;
import com.BookMyShow.entity.User;
import com.BookMyShow.exception.DependencyException;
import com.BookMyShow.exception.ResourceNotFoundException;
import com.BookMyShow.repository.BookingRepository;
import com.BookMyShow.repository.ShowsRepository;
import com.BookMyShow.repository.UserRepository;
import com.BookMyShow.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookImpl implements BookService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BookingDto booking(BookTicketRequestDto bookTicketRequestDto) {
        Optional<User> user=userRepository.findById(bookTicketRequestDto.getUserId());
        if(!user.isPresent()){
            throw new EntityNotFoundException("User not found");
        }
        Optional<Shows> shows=showsRepository.findById((int) bookTicketRequestDto.getShowId());

        if (!shows.isPresent()) {
            throw new DependencyException("Show Not Found with ID: " + bookTicketRequestDto.getUserId() + " to book ticket");
        }
        Set<String> requestedSeats=bookTicketRequestDto.getSeatsNumbers();

        List<ShowSeats> showSeats=shows.get().getSeats();

        showSeats=showSeats.stream().filter(seat->seat.getSeatType().equals(bookTicketRequestDto.getSeatType())
                && !seat.isBooked()
                && requestedSeats.contains(seat.getSeatNumber()))
						.collect(Collectors.toList());
        if(showSeats.size() !=requestedSeats.size()){
            throw new DependencyException("Seats Not Available for Booking");
        }

        Booking booking=Booking.builder().user(user.get()).shows(shows.get()).showSeats(showSeats).build();
        double amount = 0.0;
        String allotedSeats = "";
        for (ShowSeats showSeats1:showSeats){
            showSeats1.setBooked(true);
          //  showSeats1.setBookedAt(new Date());
            showSeats1.setBooking(booking);
            amount+=showSeats1.getRate();
            allotedSeats+=showSeats1.getSeatNumber();
        }
        booking.setAmount(amount);
        booking.setAllottedSeats(allotedSeats);

        Booking booked=bookingRepository.save(booking);

        return modelMapper.map(booked,BookingDto.class);

    }

    @Override
    public BookingDto getBooking(Long id) {
        Optional<Booking> booking=bookingRepository.findById(id);
        if(!booking.isPresent()){
            throw new  EntityNotFoundException("Ticket Not Found with ID: " + id);
        }
        return modelMapper.map(booking,BookingDto.class);
    }
}
