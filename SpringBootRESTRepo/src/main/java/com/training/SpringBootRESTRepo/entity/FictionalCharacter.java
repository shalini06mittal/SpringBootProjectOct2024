package com.training.SpringBootRESTRepo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "fictional_character")
public class FictionalCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150, unique = true)
    private String name;
    @Column(length = 150, nullable = false)
    private String house;

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="wandid")
    //@JsonIgnore() // REST API endpoints
    private Wand wand;

    @Column(length = 150, nullable = false)
    private String bio;

    private String imageurl;


    public FictionalCharacter(String name, String house, Wand wand, String bio, String imageurl) {
        this.name = name;
        this.house = house;
        this.wand = wand;
        this.bio = bio;
        this.imageurl = imageurl;
    }


}
