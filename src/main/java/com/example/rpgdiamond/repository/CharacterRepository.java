package com.example.rpgdiamond.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rpgdiamond.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {

}
