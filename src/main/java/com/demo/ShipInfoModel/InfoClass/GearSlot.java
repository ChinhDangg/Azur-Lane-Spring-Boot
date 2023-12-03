package com.demo.ShipInfoModel.InfoClass;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
@Table(name = "gear_slot")
public class GearSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private short efficiency1;
    private short efficiency2;
    private short efficiency3;
    private short mount_num1;
    private short mount_num2;
    private short mount_num3;

    @Column(length = 50)
    private String equipment1;
    private String equipment2;
    private String equipment3;

    @Transient
    private int[] efficiency1_num;
    @Transient
    private int[] efficiency2_num;
    @Transient
    private int[] efficiency3_num;

    @PostLoad
    private void getDecodedEfficiency() {
        efficiency1_num = decodeNumber(efficiency1);
        efficiency2_num = decodeNumber(efficiency2);
        efficiency3_num = decodeNumber(efficiency3);
    }
    public static int[] decodeNumber(int encoded) {
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
