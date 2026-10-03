package com.saeed.moviesplatform.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.aot.generate.GeneratedTypeReference;

@Entity
@Table(name = "genres")
@Getter@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;


    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
}
