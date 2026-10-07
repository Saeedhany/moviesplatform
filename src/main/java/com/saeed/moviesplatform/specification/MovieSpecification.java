package com.saeed.moviesplatform.specification;

import com.saeed.moviesplatform.dto.GenreDto;
import com.saeed.moviesplatform.entity.Genre;
import com.saeed.moviesplatform.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MovieSpecification {


    public static Specification<Movie> hasTitle(String title) {

        return (root, query, criteriaBuilder) ->{
            return criteriaBuilder.like(
                    root.get("title"),
                    "%"+title+"%"
            );
        };

    }

    public static Specification<Movie> hasGenre(Long genreId){

        return (root, query, criteriaBuilder) ->{
            return criteriaBuilder.equal(
                    root.join("genres").get("id"),
                    genreId
            );
        };
    }

    public static Specification<Movie> hasReleaseDateFrom(LocalDate date){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get("releaseDate"),
                    date
            );
        };
    }

    public static Specification<Movie> hasReleaseDateTo(LocalDate date){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get("releaseDate"),
                    date
            );
        };
    }
}
