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

public interface ItensRepository extends JpaRepository<Itens, Long>, JpaSpecificationExecutor<Itens> {

    Page<Itens> findByName(String name, Pageable pageable);
    
    Page<Itens> findByType(ItensType type, Pageable pageable);
    
    // @Query("SELECT i FROM Itens i WHERE i.price BETWEEN :minPrice AND :maxPrice")
    // Page<Itens> findByPriceRange(@Param("minPrice") Double minPrice, 
    //                            @Param("maxPrice") Double maxPrice, 
    //                            Pageable pageable);
    
    Page<Itens> findByRarity(ItensRarityType rarity, Pageable pageable);
    
    // Combinação de filtros (opcional)
    Page<Itens> findByNameAndTypeAndRarity(
        String name,
        ItensType type,
        ItensRarityType rarity,
        Pageable pageable);
}