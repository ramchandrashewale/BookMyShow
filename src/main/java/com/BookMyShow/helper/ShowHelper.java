package com.BookMyShow.helper;

import com.BookMyShow.entity.Movie;
import com.BookMyShow.entity.Screen;
import com.BookMyShow.entity.Shows;
import com.BookMyShow.entity.Theater;
import com.BookMyShow.repository.ShowsRepository;
import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class ShowHelper {

    private ShowsRepository showsRepository;

    @Autowired
    public ShowHelper(ShowsRepository showsRepository) {
        this.showsRepository = showsRepository;
    }

    public static Specification<Shows> createSpecification(String movieName, String city, String name, LocalDate showDate, LocalTime showTime) {
        List<Specification<Shows>> specifications = new ArrayList<>();

       // specifications.add(getCurrentAndFutureShowSpec());

        if (!StringUtils.isEmpty(movieName)) {
            specifications.add(getShowByMovieNameSpec(movieName));
        }

        if (!StringUtils.isEmpty(city)) {
            specifications.add(getShowByCitySpec(city));

        }
        if (!StringUtils.isEmpty(name)){
            specifications.add(getShowByTheaterSpec(name));
        }

        if (Objects.nonNull(showDate)) {
            specifications.add(getShowByDateSpec(showDate));
        }

        if (Objects.nonNull(showTime)) {
            specifications.add(getShowByTimeSpec(showTime));

        }
        System.out.println(specifications);

        Specification<Shows> combinedSpec = specifications.stream()
                .reduce((spec1, spec2) -> Specification.where(spec1).and(spec2))
                .orElse(null);
        return combinedSpec;
    }



    private static Specification<Shows> getShowByMovieNameSpec(String movieName) {
        return (root, query, criteriaBuilder) -> {
            Join<Shows, Movie> movieJoin = root.join("movie");
            return criteriaBuilder.equal(movieJoin.get("title"), movieName);
        };
    }
    public static Specification<Shows> getShowByCitySpec(String cityName){
        return (root, query, criteriaBuilder) -> {
            Join<Shows, Screen> screenJoin = root.join("screen");
            Join<Screen, Theater> theaterJoin = screenJoin.join("theater");

            return criteriaBuilder.equal(theaterJoin.get("city"), cityName);
        };
    }
    public static Specification<Shows> getShowByTheaterSpec(String theaterName){
        return (root, query, criteriaBuilder) -> {
            Join<Shows, Screen> screenJoin = root.join("screen");
            Join<Screen, Theater> theaterJoin = screenJoin.join("theater");
            return criteriaBuilder.equal(theaterJoin.get("name"), theaterName);
        };
    }
    private static Specification<Shows> getShowByDateSpec(LocalDate showDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("showDate"), showDate);
    }
    private static Specification<Shows> getShowByTimeSpec(LocalTime showTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("showTime"), showTime);
    }



}
