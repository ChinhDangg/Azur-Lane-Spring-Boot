package com.demo.ShipInfoModel.InfoClass;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
@Table(name = "augment")
public class Augment {
    @Id
    private int id;
    private String name;
    private String buff_name;
    private String buff_effect;
    private String skill_upgrade_name;
    private String skill_upgrade;
    private String stat_type1;
    private String stat_type2;
    private short stat_mod1;
    private short stat_mod2;
    private short stat_mod_range;

    @Transient
    private int[] stat_mod1_num;
    @Transient
    private int[] stat_mod2_num;
    @Transient
    private int[] stat_mod_range_num;
    @Transient
    private String image_link;

    @PostLoad
    private void initialization() {
        image_link = id + name.replace(' ','_') + ".png";
        stat_mod1_num = decodeStat(stat_mod1);
        stat_mod2_num = decodeStat(stat_mod2);
        stat_mod_range_num =  decodeStatRange(stat_mod_range);
    }

    private static int[] decodeStat(int encoded) {
        String en = Integer.toString(encoded);
        int middle = en.length()/2;
        int num1 = Integer.parseInt(en.substring(0, middle));
        int num2 = Integer.parseInt(en.substring(middle));
        return new int[] {num1, num2};
    }

    private static int[] decodeStatRange(int encoded) {
        String en = Integer.toString(encoded);
        if (en.charAt(0) != '4') {
            int delimiter = en.indexOf('4');
            int num1 = Integer.parseInt(en.substring(0, delimiter));
            int num2 = Integer.parseInt(en.substring(delimiter));
            return new int[]{num1, num2};
        }
        else return new int[] {0, Integer.parseInt(String.valueOf(en.charAt(1)))};
    }
}
