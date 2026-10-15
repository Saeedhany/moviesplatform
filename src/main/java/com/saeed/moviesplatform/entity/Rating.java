package com.saeed.moviesplatform.entity;


import com.saeed.moviesplatform.dto.UserResponse;
import com.saeed.moviesplatform.security.CustomUserDetails;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ratings",
    uniqueConstraints = @UniqueConstraint(
            columnNames = {"USER_ID", "MOVIE_ID"}
    ))
@Getter@Setter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private PlatformUser user;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movie movie;

    @Column(name = "RATE", nullable = false)
    private Integer rate;

}
