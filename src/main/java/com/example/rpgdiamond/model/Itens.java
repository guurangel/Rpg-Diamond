package com.example.rpgdiamond.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    private String name;

    @NotNull(message = "campo obrigatório")
    @Enumerated(EnumType.STRING)
    private ItensType type;

    @NotNull(message = "campo obrigatório")
    @ManyToOne
    private Character character;

    @NotNull(message = "campo obrigatório")
    @Enumerated(EnumType.STRING)
    private ItensRarityType rarity;

    @Positive(message = "deve ser positivo")
    private double preco;
}
