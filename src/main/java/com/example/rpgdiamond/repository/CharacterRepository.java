package com.example.rpgdiamond.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.rpgdiamond.model.Character;
import com.example.rpgdiamond.model.CharacterType;

public interface CharacterRepository extends JpaRepository<Character, Long>, JpaSpecificationExecutor<Character> {

    Page<Character> findByNameContainingIgnoringCaseAndType(String name, CharacterType type, Pageable pageable);

    Page<Character> findByType(CharacterType type, Pageable pageable);
}