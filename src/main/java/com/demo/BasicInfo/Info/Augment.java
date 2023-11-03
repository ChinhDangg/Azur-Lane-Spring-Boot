package com.demo.BasicInfo.Info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
@Table(name = "augment")
public class Augment {
    @Id
    private int id;
    private String name;
    private String mod_effect_name;
    private String mod_effect;
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

    @PostLoad
    private void getDecodedStat() {
        stat_mod1_num = decodeNumber(stat_mod1);
        stat_mod2_num = decodeNumber(stat_mod2);
        stat_mod_range_num = decodeNumber(stat_mod_range);
    }

    private static int[] decodeNumber(int encoded) {
        String num1 = "", num2 = "";
        String str_encoded = Integer.toString(encoded);
        if (encoded < 100) {
            num1 = str_encoded.charAt(0) + "5";
            num2 = str_encoded.charAt(1) + "5";
        }
        else if (encoded < 1000) {
            num1 = (str_encoded.charAt(1) == '0') ? str_encoded.substring(0, 2) : (str_encoded.charAt(0) + "5");
            num2 = (str_encoded.charAt(1) == '0') ? (str_encoded.charAt(2) + "5") : (str_encoded.substring(1) + "0");
            if (Integer.parseInt(num2) - Integer.parseInt(num1) > 100)
                num2 = str_encoded.substring(1);
        }
        else {
            int n4 = str_encoded.charAt(3);
            num2 = str_encoded.substring(2);
            num2 = (n4 == '0' || n4 == '5') ? ("1" + num2) : (num2 + "0");
            int n2 = str_encoded.charAt(1);
            num1 = str_encoded.substring(0, 2);
            num1 = (n2 == '0' || n2 == '5') ? ("1" + num1) : (num1 + "0");
            if (Integer.parseInt(num1) > Integer.parseInt(num2))
                num1 = str_encoded.substring(0, 2);
        }
        return new int[] { Integer.parseInt(num1), Integer.parseInt(num2) };
    }
}
