package com.demo.BasicInfo.Info;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;

@Data
@MappedSuperclass
public class Stats {
    @Id
    protected int id;

    protected short health;
    protected short firepower;
    protected short torpedo;
    protected short aviation;
    protected short anti_air;
    protected short reload;
    protected short evasion;
    protected short speed;
    protected short accuracy;
    protected short luck;

    @Column(name = "anti_submarine")
    protected short anti_sub;

    @Column(name = "oil_cost")
    protected short oil_cost;

    @Transient
    protected HashMap<String, Short> stats;
    @PostLoad
    protected void getStatMap() {
        stats = new HashMap<>();
        stats.put("Health", health);
        stats.put("Firepower", firepower);
        stats.put("Torpedo", torpedo);
        stats.put("Aviation", aviation);
        stats.put("Anti-air", anti_air);
        stats.put("Reload", reload);
        stats.put("Evasion", evasion);
        stats.put("Speed", speed);
        stats.put("Accuracy", accuracy);
        stats.put("Luck", luck);
        stats.put("Anti-sub", anti_sub);
        stats.put("Oil-cost", oil_cost);
    }
}
