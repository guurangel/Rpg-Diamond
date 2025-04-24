package com.example.rpgdiamond.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.rpgdiamond.model.Itens;
import com.example.rpgdiamond.model.ItensType;
import com.example.rpgdiamond.model.ItensRarityType;

public interface ItensRepository extends 
    JpaRepository<Itens, Long>, 
    JpaSpecificationExecutor<Itens> {

    Page<Itens> findByName(String name, Pageable pageable);
    
    Page<Itens> findByType(ItensType type, Pageable pageable);
    
    Page<Itens> findByRarity(ItensRarityType rarity, Pageable pageable);
    
    Page<Itens> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);
    
    Page<Itens> findByNameAndTypeAndRarityAndPriceBetween(
        String name,
        ItensType type,
        ItensRarityType rarity,
        Integer minPrice,
        Integer maxPrice,
        Pageable pageable);
}