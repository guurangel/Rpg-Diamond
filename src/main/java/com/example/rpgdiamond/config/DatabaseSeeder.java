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
    // Criação dos personagens (mantido igual)
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

    // Lista de itens pré-definidos com tipos corretos
    var predefinedItems = List.of(
        // Armasss
        new ItemData("Espada Flamejante", ItensType.Armas, ItensRarityType.Raro, 500),
        new ItemData("Espada de Gelo", ItensType.Armas, ItensRarityType.Raro, 450),
        new ItemData("Machado de Batalha", ItensType.Armas, ItensRarityType.Comum, 300),
        new ItemData("Arco Élfico", ItensType.Armas, ItensRarityType.Épico, 600),
        new ItemData("Lança Celestial", ItensType.Armas, ItensRarityType.Lendário, 800),
        new ItemData("Adaga Sombria", ItensType.Armas, ItensRarityType.Raro, 350),
        
        // Armasssduras
        new ItemData("Armasssdura de Placas", ItensType.Armadura, ItensRarityType.Raro, 400),
        new ItemData("Túnica do Mago", ItensType.Armadura, ItensRarityType.Épico, 550),
        new ItemData("Couro Reforçado", ItensType.Armadura, ItensRarityType.Comum, 250),
        new ItemData("Escudo Divino", ItensType.Armadura, ItensRarityType.Lendário, 700),
        
        // Poções
        new ItemData("Poção de Cura", ItensType.Poção, ItensRarityType.Comum, 100),
        new ItemData("Poção de Mana", ItensType.Poção, ItensRarityType.Comum, 120),
        new ItemData("Poção de Invisibilidade", ItensType.Poção, ItensRarityType.Raro, 300),
        new ItemData("Elixir da Vida", ItensType.Poção, ItensRarityType.Lendário, 1000),
        
        // Acessórios
        new ItemData("Anel de Poder", ItensType.Acessório, ItensRarityType.Épico, 650),
        new ItemData("Amuleto de Proteção", ItensType.Acessório, ItensRarityType.Raro, 400),
        new ItemData("Capo da Invisibilidade", ItensType.Acessório, ItensRarityType.Lendário, 900),
        new ItemData("Botas Velozes", ItensType.Acessório, ItensRarityType.Raro, 350)
    );

    var itens = new ArrayList<Itens>();
    var random = new Random();

    // Cria 50 itens variados
    for (int i = 0; i < 50; i++) {
        Character owner = savedCharacters.get(i % 10);
        ItemData itemData = predefinedItems.get(random.nextInt(predefinedItems.size()));
        
        itens.add(Itens.builder()
            .name(itemData.name())
            .type(itemData.type())
            .character(owner)
            .rarity(itemData.rarity())
            .price(itemData.price()) // Preço fixo definido para cada item
            .build());
    }

    itensRepository.saveAll(itens);
    }

    private record ItemData(String name, ItensType type, ItensRarityType rarity, int price) {}
}
