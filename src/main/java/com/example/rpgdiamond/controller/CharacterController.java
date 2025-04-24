package com.example.rpgdiamond.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.rpgdiamond.model.Character;
import com.example.rpgdiamond.model.CharacterType;
import com.example.rpgdiamond.repository.CharacterRepository;
import com.example.rpgdiamond.specification.CharacterSpecification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("characters")
@Slf4j
public class CharacterController {

    public record CharacterFilters(String name, CharacterType type) {
    }

    @Autowired
    private CharacterRepository repository;

    @GetMapping
    public Page<Character> index(
            CharacterFilters filters,
            @PageableDefault(size = 10) Pageable pageable) {

                var specification = CharacterSpecification.withFilters(filters);
                return repository.findAll(specification, pageable);
    }

    @PostMapping
    @CacheEvict(value = "characters", allEntries = true)
    @Operation(responses = @ApiResponse(responseCode = "400", description = "Validação falhou"))
    @ResponseStatus(code = HttpStatus.CREATED)
    public Character create(@RequestBody @Valid Character character) {
        return repository.save(character);
    }

    @GetMapping("{id}")
    public ResponseEntity<Character> get(@PathVariable Long id) {
        return ResponseEntity.ok(getCharacter(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Character> delete(@PathVariable Long id) {
        repository.delete(getCharacter(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Character> update(@PathVariable Long id, @RequestBody @Valid Character character) {
        getCharacter(id);
        character.setId(id);
        repository.save(character);
        return ResponseEntity.ok(character);
    }
    private Character getCharacter(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
    }
}