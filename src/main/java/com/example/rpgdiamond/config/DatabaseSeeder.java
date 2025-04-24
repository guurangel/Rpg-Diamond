package com.example.rpgdiamond.config;

import com.example.rpgdiamond.model.Character;
import com.example.rpgdiamond.model.CharacterType;
import com.example.rpgdiamond.model.Itens;
import com.example.rpgdiamond.model.ItensRarityType;
import com.example.rpgdiamond.model.ItensType;
import com.example.rpgdiamond.repository.CharacterRepository;
import com.example.rpgdiamond.repository.ItensRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ItensRepository itensRepository;


    @PostConstruct
    public void init() {
        var characters = List.of(
            Character.builder().name("Aragorn").type(CharacterType.Guerreiro).level(45).coins(1000).build(),
            Character.builder().name("Gandalf").type(CharacterType.Mago).level(99).coins(5000).build(),
            Character.builder().name("Legolas").type(CharacterType.Arqueiro).level(60).coins(2500).build(),
            Character.builder().name("Conan").type(CharacterType.Guerreiro).level(55).coins(1800).build(),
            Character.builder().name("Merlin").type(CharacterType.Mago).level(85).coins(3500).build(),
            Character.builder().name("Robin").type(CharacterType.Arqueiro).level(40).coins(1200).build(),
            Character.builder().name("Boromir").type(CharacterType.Guerreiro).level(50).coins(1500).build(),
            Character.builder().name("Saruman").type(CharacterType.Mago).level(90).coins(4000).build(),
            Character.builder().name("Hawkeye").type(CharacterType.Arqueiro).level(48).coins(2000).build(),
            Character.builder().name("Hercules").type(CharacterType.Guerreiro).level(75).coins(3000).build()
        );

        var savedCharacters = characterRepository.saveAll(characters);

        var itemNames = List.of(
            "Espada Flamejante", "Arco Élfico", "Cajado Arcano", "Machado de Batalha", 
            "Poção de Cura", "Armadura de Placas", "Amuleto de Proteção", "Anel de Poder",
            "Escudo Resistente", "Dagas Gêmeas", "Livro de Feitiços", "Botas Velozes",
            "Capo da Invisibilidade", "Elmo Sagrado", "Luvas de Ferro"
        );

        var itens = new ArrayList<Itens>();
        var random = new Random();

        for (int i = 0; i < 50; i++) {
            Character owner = savedCharacters.get(i % 10);
            
            itens.add(Itens.builder()
                .name(itemNames.get(random.nextInt(itemNames.size())))
                .type(ItensType.values()[random.nextInt(ItensType.values().length)])
                .character(owner)
                .rarity(ItensRarityType.values()[random.nextInt(ItensRarityType.values().length)])
                .preco(10 + random.nextDouble() * 990)
                .build());
        }

        itensRepository.saveAll(itens);
    }
}