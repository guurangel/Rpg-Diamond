package com.example.rpgdiamond.specification;

import java.util.ArrayList;
import org.springframework.data.jpa.domain.Specification;

import com.example.rpgdiamond.controller.ItensController.ItensFilters;
import com.example.rpgdiamond.model.Itens;

import jakarta.persistence.criteria.Predicate;

public class ItensSpecification {

    public static Specification<Itens> withFilters(ItensFilters filters){
        return (root, query, cb) -> {  //lambda expresion 
            var predicates = new ArrayList<>();

            if (filters.name() != null){
                predicates.add(
                    cb.like(
                        cb.lower(root.get("name")), "%" + filters.name().toLowerCase() + "%"
                    )
                );
            }

            if (filters.rarity() != null) {
                predicates.add(
                    cb.like(
                        cb.lower(root.get("rarity")),
                        "%" + filters.rarity().name().toLowerCase() + "%"
                    )
                );
            }
            
            if (filters.type() != null) {
                predicates.add(
                    cb.like(
                        cb.lower(root.get("type")),
                        "%" + filters.type().name().toLowerCase() + "%"
                    )
                );
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}