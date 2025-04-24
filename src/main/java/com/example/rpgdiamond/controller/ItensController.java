package com.example.rpgdiamond.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import com.example.rpgdiamond.model.Itens;
import com.example.rpgdiamond.repository.ItensRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("itens")
@Slf4j
public class ItensController {

    @Autowired
    private ItensRepository repository;

    @GetMapping
    @Cacheable("itens")
    public List<Itens> index() {
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "itens", allEntries = true)
    @Operation(responses = @ApiResponse(responseCode = "400", description = "Validação falhou"))
    @ResponseStatus(code = HttpStatus.CREATED)
    public Itens create(@RequestBody @Valid Itens itens) {
        return repository.save(itens);
    }

    @GetMapping("{id}")
    public ResponseEntity<Itens> get(@PathVariable Long id) {
        return ResponseEntity.ok(getItens(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Itens> delete(@PathVariable Long id) {
        repository.delete(getItens(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Itens> update(@PathVariable Long id, @RequestBody @Valid Itens itens) {
        getItens(id);
        itens.setId(id);
        repository.save(itens);
        return ResponseEntity.ok(itens);
    }

    private Itens getItens(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }
}