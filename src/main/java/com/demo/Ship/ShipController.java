package com.demo.Ship;

import com.demo.BasicInfo.Info.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/ship_list")
public class ShipController {
    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/ships/{ship_name}")
    public String getShipInfo(@PathVariable("ship_name") String name, Model model) {
        int id = 0;
        try {
            id = Integer.parseInt(name);
        } catch(Exception e) {
            System.out.println("Ship name is passed instead of id");
            id = shipService.getShipIdByName(name);
        }
        Ships ship = shipService.getShipBasicInfo(id);
        model.addAttribute("ship", ship);
        model.addAttribute("gearSlot", shipService.getGearSlotById(id));
        model.addAttribute("augment", shipService.getAugmentById(id));
        model.addAttribute("stat120", shipService.getShipStat120(id));
        model.addAttribute("imagePos", shipService.getShipImagePosition(id));
        List<Skills> skills = shipService.getShipCombinedSkill(id);
        if (skills.get(0).getSkill_name().contains("Fate")) {
            model.addAttribute("fate", true);
            model.addAttribute("skill1", skills.get(1));
            model.addAttribute("fate_skill1", skills.get(0));
        }
        else {
            model.addAttribute("fate", false);
            model.addAttribute("skill1", skills.get(0));
        }
        if (ship.getAcquisition().equals("Research"))
            model.addAttribute("fate_desc", true);
        model.addAttribute("skill_num", skills.get(skills.size()-1).getSkill_num());
        return "ship_info";
    }

    @GetMapping("/skill/{id}/{skill_num}")
    @ResponseBody
    public List<Skills> getShipSkill(@PathVariable("id") int id, @PathVariable("skill_num") int num) {
        return shipService.getShipCombinedSkillByNum(id, num);
    }

    @GetMapping("/stat125/{id}")
    @ResponseBody
    public HashMap<String, Short> getShipStat125(@PathVariable("id") int id) {
        return shipService.getShipStat125(id).getStats();
    }

    @GetMapping()
    public String getShipList() {
        return "list_of_ship_image";
    }
    @GetMapping("/class/{class}")
    public String getShipListByClass(@PathVariable("class") String c, Model model) {
        List<Object[]> ship_list = shipService.getShipListByClass(c);
        model.addAttribute("ship_list", ship_list);
        return "list_of_ship_image :: content";
    }
    @GetMapping("/rarity/{rarity}")
    public String getShipListByRarity(@PathVariable("rarity") String rarity, Model model) {
        List<Object[]> ship_list = shipService.getShipListByRarity(rarity);
        model.addAttribute("ship_list", ship_list);
        return "list_of_ship_image :: content";
    }
}
