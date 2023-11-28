package com.demo.ShipInfoModel.InfoClass;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shipImage_position")
public class ShipPosition {
    @Id
    private int id;

    @Column(name = "left_pos")
    private short left;

    @Column(name = "top_pos")
    private short top;

    private short width;
    private short height;
}
