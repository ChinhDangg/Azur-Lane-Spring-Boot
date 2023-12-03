package com.demo.ShipInfoModel.InfoClass;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "ships")
public class Ship {
    @Id
    @SequenceGenerator(
            name = "ship_sequence",
            sequenceName = "ship_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "ship_sequence"
    )
    private int id;
    @Column(name = "ship_name", nullable = false, unique = true, length = 60)
    private String name;

    @Column(nullable = false, length = 30)
    private String rarity;

    @Column(nullable = false, length = 20)
    private String classification;

    @Column(nullable = false, length = 30)
    private String faction;

    @Column(name = "class", nullable = false, length = 20)
    private String ship_class;

    @Column(name = "construction", nullable = false)
    private LocalTime construction_time;

    @Column(nullable = false, length = 10)
    private String armor;

    @Transient
    private String faction_link; //iron blood, eagle union
    @Transient
    private String classification_link; //destroyer, battleship,...
    @Transient
    private String rarity_link;
    @Transient
    private String rarity_class;
    @Transient
    private String acquisition;
    @Transient
    private String class_augment;
    @Transient
    private short star_num;
    @Transient
    private String bg_image_name;

    @PostLoad
    private void getImageLink() {
        faction_link = "nation_" + faction.toLowerCase().replace(" ", "_");
        classification_link = "type_" + classification.toLowerCase().replace(" ", "_");
        rarity_link = "rarity_" + rarity.toLowerCase().replace(" ", "_");
        rarity_class = rarity.toLowerCase().replace(" ", "_");
        acquisition = initializeAcquisition(construction_time);
        class_augment = initializeClassAugment(classification);
        star_num = initializeNumberOfStar(rarity);
        bg_image_name = "ship"+id+"_"+name.replace(' ','_')+".png";
    }

    private String initializeAcquisition(LocalTime time) {
        if (time.equals(LocalTime.of(11,11,11)))
            return "Research";
        else if (time.equals(LocalTime.of(0,0,0)))
            return "Acquisition";
        else if (time.equals(LocalTime.of(10,10,10)))
            return "Shop / Map";
        return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private String initializeClassAugment(String c) {
        if (c.equals("Destroyer"))
            return "Hammer / Dual Sword";
        else if (c.contains("Battleship"))
            return "Bowgun / Officer's Sword";
        else if (c.contains("Large") || c.contains("Heavy"))
            return "Lance / Greatsword";
        else if (c.contains("Aircraft"))
            return "Scepter / Hunting Bow";
        else
            return "Crossbow / Sword";
    }

    private short initializeNumberOfStar(String rarity) {
        return switch(rarity) {
          case ("Ultra Rare"), ("Decisive"), ("Super Rare") -> 6;
          case ("Elite"), ("Rare") -> 5;
          case ("Normal") -> 4;
          default -> throw new IllegalStateException();
        };
    }
}





















