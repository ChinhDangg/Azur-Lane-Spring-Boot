package com.demo.ShipInfoModel.InfoClass;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "skins")
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int ship_id;
    private String skin_name;

    @Transient
    private String skin_image_name;

    @PostLoad
    private void initializeSkinURL() {
        skin_image_name = "skin"+id+"_"+ship_id; //will also add the ship name at the end in ship service
    }
}
