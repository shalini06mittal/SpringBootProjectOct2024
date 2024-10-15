package com.training.SpringBootRESTRepo.repo;

import com.training.SpringBootRESTRepo.entity.FictionalCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepo extends JpaRepository<FictionalCharacter,Integer >
 {

    public List<FictionalCharacter> findAllByHouse(String house);
    public FictionalCharacter findByName(String name);
    public boolean existsByName(String name);
}
