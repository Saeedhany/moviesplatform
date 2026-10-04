package com.saeed.moviesplatform.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "actors")
@Getter@Setter
public class Actor {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PHOTO", nullable = true)
    private String photo;

    @Lob
    @Column(name = "BIOGRAPHY", nullable = true)
    private String biography;



}
