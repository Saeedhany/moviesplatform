package com.saeed.moviesplatform.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "movies")
@Getter@Setter
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Lob
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TRAILER")
    private String trailer;

    @Column(name = "POSTER", nullable = false)
    private String poster;

    @Column(name = "RELEASE_DATE", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "DURATION", nullable = false)
    private Integer duration;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;
}
