package com.demo.Ship;

import com.demo.BasicInfo.Info.*;
import com.demo.BasicInfo.Repositories.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final Stat120Repository stat120Repository;
    private final Stat125Repository stat125Repository;
    private final GearSlotRepository gearSlotRepository;
    private final AugmentRepository augmentRepository;
    private final SkillRepository skillRepository;
    private final ShipImagePosRepository shipImagePosRepository;

    public ShipService(ShipRepository shipRepository,
                       Stat120Repository stat120Repository,
                       Stat125Repository stat125Repository,
                       GearSlotRepository gearSlotRepository,
                       AugmentRepository augmentRepository,
                       SkillRepository skillRepository,
                       ShipImagePosRepository shipImagePosRepository) {
        this.shipRepository = shipRepository;
        this.stat120Repository = stat120Repository;
        this.stat125Repository = stat125Repository;
        this.gearSlotRepository = gearSlotRepository;
        this.augmentRepository = augmentRepository;
        this.skillRepository = skillRepository;
        this.shipImagePosRepository = shipImagePosRepository;
    }

    public Ships getShipBasicInfo(int id) {
        return shipRepository.findById(id).orElse(null);
    }
    public Stat120 getShipStat120(int id) { return stat120Repository.findById(id).orElse(null); }
    public Stat125 getShipStat125(int id) { return stat125Repository.findById(id).orElse(null); }
    public GearSlot getGearSlotById(int id) {
        return gearSlotRepository.findById(id).orElse(null);
    }
    public Augment getAugmentById(int id) {
        return augmentRepository.findById(id).orElse(null);
    }

    public ShipPosition getShipImagePosition(int id) { return shipImagePosRepository.findById(id).orElse(null); }
    public List<Skills> getShipCombinedSkill(int id) {
        List<Skills> temp = new ArrayList<>();
        List<Object[]> query_skill = skillRepository.findCombinedSkill(id);
        for (Object[] objects : query_skill)
            temp.add(new Skills((String) objects[0], (String) objects[1], (int)objects[2]));
        return temp;
    }

    public List<Skills> getShipCombinedSkillByNum(int id, int num) {
        List<Skills> temp = new ArrayList<>();
        List<Object[]> query_skill = skillRepository.findCombinedSkillByNum(id, num);
        for (Object[] objects : query_skill)
            temp.add(new Skills((String) objects[0], (String) objects[1]));
        return temp;
    }

    public List<Skills> getShipSkills(int id) { return skillRepository.findSkillByShipId(id); }
    public int getShipIdByName(String name) { return shipRepository.getShipIdByName(name); }

    public List<Object[]> getShipListByRarity(String rarity) {
        return switch (rarity) {
            case ("ultra") -> shipRepository.getUltraRareShip();
            case ("super") -> shipRepository.getSuperRareShip();
            case ("elite") -> shipRepository.getEliteShip();
            case ("rare") -> shipRepository.getRareShip();
            case ("normal") -> shipRepository.getNormalShip();
            case ("all") -> shipRepository.getAllShipByRarity();
            default -> throw new IllegalStateException("Unexpected value: " + rarity);
        };
    }

    public List<Object[]> getShipListByClass(String c) {
        return switch (c) {
            case ("bb") -> shipRepository.getBBShip();
            case ("cv") -> shipRepository.getCVShip();
            case ("ca") -> shipRepository.getCAShip();
            case ("cl") -> shipRepository.getCLShip();
            case ("dd") -> shipRepository.getDDShip();
            case ("ss") -> shipRepository.getSSShip();
            case ("other") -> shipRepository.getOtherShip();
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

}
