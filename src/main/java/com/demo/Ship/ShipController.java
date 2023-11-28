package com.demo.Ship;

import com.demo.ShipInfoModel.InfoClass.Augment;
import com.demo.ShipInfoModel.InfoClass.Ship;
import com.demo.ShipInfoModel.InfoClass.Skill;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        Ship ship = shipService.getShipBasicInfo(id);
        model.addAttribute("ship", ship);
        System.out.println(ship.getBg_image_name());
        model.addAttribute("gearSlot", shipService.getGearSlotById(id));
        model.addAttribute("augment", shipService.getAugmentById(id));
        model.addAttribute("stat120", shipService.getShipStat120(id));
        model.addAttribute("imagePos", shipService.getShipImagePosition(id));
        List<Skill> skills = shipService.getCombinedSkill(id);
        if (skills.get(0).getSkill_name().contains("Fate")) {
            model.addAttribute("fate", true);
            model.addAttribute("skill1", skills.get(1));
            model.addAttribute("fate_skill1", skills.get(0));
        }
        else {
            model.addAttribute("skill1", skills.get(0));
        }
        if (ship.getAcquisition().equals("Research"))
            model.addAttribute("fate_desc", true);
        model.addAttribute("skill_num", skills.get(skills.size()-1).getSkill_num());
        Integer skin_id = shipService.getSkinIdByShipId(id);
        if (skin_id != null)
            model.addAttribute("skinImagePos", shipService.getShipImagePosition(skin_id));
        return "ship_info";
    }

    @GetMapping("/skill/{id}/{skill_num}")
    @ResponseBody
    public List<Skill> getShipSkill(@PathVariable("id") int id, @PathVariable("skill_num") int num) {
        return shipService.getCombinedSkillByNum(id, num);
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getShipOneSkinImage(@PathVariable("id") int id) throws IOException {
        String url = shipService.getShipOneSkinURL(id);
        Resource imageResource = new ClassPathResource(url);
        byte[] imageBytes = Files.readAllBytes(imageResource.getFile().toPath());
        CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .cacheControl(cacheControl)
                .body(imageBytes);
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
        List<Ship> ship_list = shipService.getShipsByClassGroup(c);
        model.addAttribute("ship_list", ship_list);
        return "list_of_ship_image :: content";
    }
    @GetMapping("/rarity/{rarity}")
    public String getShipListByRarity(@PathVariable("rarity") String rarity, Model model) {
        List<Ship> ship_list = shipService.getShipsByRarity(rarity);
        model.addAttribute("ship_list", ship_list);
        return "list_of_ship_image :: content";
    }
}
