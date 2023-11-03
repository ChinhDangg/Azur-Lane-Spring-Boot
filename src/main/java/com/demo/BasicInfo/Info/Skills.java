package com.demo.BasicInfo.Info;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "skills")
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ship_id")
    private int shipId;

    @Column(name = "skill_name")
    private String skill_name;

    @Column(name = "skill_description", columnDefinition = "text")
    private String skill_description;

    @Column(name = "skill_num")
    private int skill_num;

    public Skills() {}

    public Skills(String skill_name, String skill_description, int skill_num) {
        this.skill_name = skill_name;
        this.skill_description = skill_description;
        this.skill_num = skill_num;
    }

    public Skills(String skill_name, String skill_description) {
        this.skill_name = skill_name;
        this.skill_description = skill_description;
    }

}
