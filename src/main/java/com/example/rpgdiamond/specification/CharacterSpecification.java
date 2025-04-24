package com.example.rpgdiamond.specification;

import java.util.ArrayList;
import org.springframework.data.jpa.domain.Specification;

import com.example.rpgdiamond.controller.CharacterController.CharacterFilters;
import com.example.rpgdiamond.model.Character;
import jakarta.persistence.criteria.Predicate;

public class CharacterSpecification {

    public static Specification<Character> withFilters(CharacterFilters filters){
        return (root, query, cb) -> {  //lambda expresion 
            var predicates = new ArrayList<>();

            if (filters.name() != null){
                predicates.add(
                    cb.like(
                        cb.lower(root.get("name")), "%" + filters.name().toLowerCase() + "%"
                    )
                );
            }

            if (filters.type() != null) {
                predicates.add(
                    cb.like(
                        cb.lower(root.get("type")),
                        "%" + filters.type().toString().toLowerCase() + "%"
                    )
                );
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}