package com.saeed.moviesplatform.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "watchlist",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"USER_ID", "MOVIE_ID"}
        )
)

@Getter@Setter
public class Watchlist {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private PlatformUser user;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movie movie;

}
