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
@Table(name = "wand")
public class Wand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String wood;
    private String core;
    private String length;

//    @OneToOne(mappedBy = "wand")
//    @JsonIgnore
//    private FictionalCharacter character;


    public Wand(String wood, String core, String length) {
        this.wood = wood;
        this.core = core;
        this.length = length;
    }


}

