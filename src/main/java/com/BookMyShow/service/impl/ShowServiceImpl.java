package com.BookMyShow.service.impl;

import com.BookMyShow.dto.ShowDto;
import com.BookMyShow.entity.Movie;
import com.BookMyShow.entity.Screen;
import com.BookMyShow.entity.ShowSeats;
import com.BookMyShow.entity.Shows;
import com.BookMyShow.enums.SeatType;
import com.BookMyShow.exception.DuplicateRecordException;
import com.BookMyShow.helper.ShowHelper;
import com.BookMyShow.repository.MovieRepository;
import com.BookMyShow.repository.ScreenRepository;
import com.BookMyShow.repository.ShowSeatsRepository;
import com.BookMyShow.repository.ShowsRepository;
import com.BookMyShow.service.ShowService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ShowSeatsRepository showSeatsRepository;


    @Autowired
    private ShowHelper showHelper;
    @Override
    public ShowDto createShow(ShowDto showDto) {
        Optional<Movie>optionalMovie=movieRepository.findByTitle(showDto.getMovie().getTitle());
        if(!optionalMovie.isPresent()){
            throw new DuplicateRecordException("movie with   this title is not present");
        }

        Optional<Screen> screen=screenRepository.findById(showDto.getScreen().getScreen_id());
        if(!screen.isPresent()){
            throw new EntityNotFoundException("Screen with this id is not present");
        }
        Shows shows=modelMapper.map(showDto,Shows.class);

        shows.setMovie(optionalMovie.get());
        shows.setScreen(screen.get());
        Shows saveShows=showsRepository.save(shows);

        List<ShowSeats> seats=getShowSeats(saveShows);


        for(ShowSeats showSeats:seats){
            showSeats.setShows(shows);
        }
        seats=showSeatsRepository.saveAll(seats);


        saveShows.setSeats(seats);



        return modelMapper.map(saveShows,ShowDto.class);
    }

    private List<ShowSeats> getShowSeats(Shows shows){
        List<ShowSeats> seats=new ArrayList<>();
        for (char row = 'A'; row <= 'E'; row++) {
            for (int col = 1; col <= 2; col++) {
                String seatNumber = col + String.valueOf(row);
                SeatType seatType = (col == 1) ? SeatType.CLASSIC : SeatType.PREMIUM;
                int rate = (col == 1) ? 100 : 150;
                seats.add(getShowSeat(seatNumber, seatType, rate,shows));
            }
        }



        return  seats;
    }
    private ShowSeats getShowSeat(String seatNumber, SeatType seatType, int rate,Shows shows){
        return ShowSeats.builder().seatNumber(seatNumber).seatType(seatType).rate(rate).shows(shows).build();
    }

    @Override
    public List<ShowDto> searchShow(String movieName, String city, String theaterName, LocalDate showDate, LocalTime showTime, int pageNo, int limit) {
       Specification<Shows> specifications = ShowHelper.createSpecification(movieName, city,theaterName, showDate, showTime);


        //System.out.println(specifications);
        Pageable p=PageRequest.of(pageNo,limit);

        List<Shows> showsList=showsRepository.findAll(specifications);
        //Page<Shows> showsPage = showsRepository.findAll(specifications,p);
        //List<ShowDto> showDtoList=showsPage.getContent().stream().map(shows -> modelMapper.map(shows,ShowDto.class)).collect(Collectors.toList());

        List<ShowDto> showDtoList=showsList.stream().map(shows -> modelMapper.map(shows,ShowDto.class)).collect(Collectors.toList());

//        PageResponse<ShowDto> pageResponse = new PageResponse<>();
//
//
//        if (showsPage.hasContent()) {
//            pageResponse.setNumber(pageNo);
//            pageResponse.setRecords(showsPage.getNumberOfElements());
//
//            pageResponse.setTotalPages(showsPage.getTotalPages());
//            pageResponse.setTotalRecords(showsPage.getTotalElements());
//            pageResponse.setData(showDtoList);
//
//        }

        return showDtoList;

    }


}
