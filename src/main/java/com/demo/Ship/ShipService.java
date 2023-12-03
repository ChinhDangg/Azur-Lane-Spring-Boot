package com.demo.Ship;

import com.demo.ShipInfoModel.InfoClass.*;
import com.demo.ShipInfoModel.InfoEnum.Classification;
import com.demo.ShipInfoModel.Repositories.*;
import jakarta.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShipService {
    private static final Logger logger = LoggerFactory.getLogger(ShipService.class);
    @Autowired
    private final ShipRepository shipRepository;
    private final Stat120Repository stat120Repository;
    private final Stat125Repository stat125Repository;
    private final GearSlotRepository gearSlotRepository;
    private final AugmentRepository augmentRepository;
    private final SkillRepository skillRepository;
    private final ShipImagePosRepository shipImagePosRepository;
    private final SkinRepository skinRepository;

    public ShipService(ShipRepository shipRepository,
                       Stat120Repository stat120Repository,
                       Stat125Repository stat125Repository,
                       GearSlotRepository gearSlotRepository,
                       AugmentRepository augmentRepository,
                       SkillRepository skillRepository,
                       ShipImagePosRepository shipImagePosRepository,
                       SkinRepository skinRepository) {
        this.shipRepository = shipRepository;
        this.stat120Repository = stat120Repository;
        this.stat125Repository = stat125Repository;
        this.gearSlotRepository = gearSlotRepository;
        this.augmentRepository = augmentRepository;
        this.skillRepository = skillRepository;
        this.shipImagePosRepository = shipImagePosRepository;
        this.skinRepository = skinRepository;
    }

    public Ship getShipBasicInfo(int id) {
        logger.info("Ship repository finding ship by id {}",id);
        Optional<Ship> s = shipRepository.findById(id);
        if (s.isPresent())
            return s.get();
        logger.warn("At getShipBasicInfo, ship not found with id {}",id);
        return null;
    }
    public Stat120 getShipStat120(int id) {
        logger.info("Stat120 repository finding ship stat by id {}",id);
        Optional<Stat120> stat = stat120Repository.findById(id);
        if (stat.isPresent())
            return stat.get();
        logger.warn("At getShipStat120, ship stat not found with id {}",id);
        return null;
    }
    public Stat125 getShipStat125(int id) {
        logger.info("Stat125 repository finding ship stat by id {}",id);
        Optional<Stat125> stat = stat125Repository.findById(id);
        if (stat.isPresent())
            return stat.get();
        logger.warn("At getShipStat125, ship stat not found with id {}",id);
        return null;
    }
    public GearSlot getGearSlotById(int id) {
        logger.info("GearSlot repository finding ship gear info with id {}",id);
        Optional<GearSlot> g = gearSlotRepository.findById(id);
        if (g.isPresent())
            return g.get();
        logger.warn("At getGearSlotById, ship gear info not found with id {}",id);
        return null;
    }
    public Augment getAugmentById(int id) {
        logger.info("Augment repository finding ship augment with id {}",id);
        Optional<Augment> a = augmentRepository.findById(id);
        if (a.isPresent())
            return a.get();
        logger.warn("At getAugmentById, ship augment not found with id {}",id);
        return null;
    }
    public ShipPosition getShipImagePosition(int id) {
        logger.info("ShipImagePos repository finding image loc with id {}",id);
        Optional<ShipPosition> sp = shipImagePosRepository.findById(id);
        if (sp.isPresent())
            return sp.get();
        logger.warn("At getShipImagePosition, ship image position not found with id {}",id);
        return null;
    }

    public List<Skill> getCombinedSkill(int id) {
        try {
            logger.info("Skill repository finding combined skill by id {}",id);
            List<Object[]> s =  skillRepository.findCombinedSkill(id);
            if (!s.isEmpty()) {
                List<Skill> temp = new ArrayList<>();
                for (Object[] objects : s)
                    temp.add(new Skill((String) objects[0], (String) objects[1], (int) objects[2]));
                return temp;
            }
            logger.warn("Skill not found with id {}",id);
            return null;
        }
        catch (PersistenceException | InvalidDataAccessResourceUsageException ex) {
            logger.warn("Invalid query at getCombinedSkill with skill repository findCombinedSkill");
            throw new PersistenceException();
        }
    }

    public List<Skill> getCombinedSkillByNum(int id, int num) {
        try {
            logger.info("Skill repository finding combined skill by id {} and num {}",id,num);
            List<Object[]> s = skillRepository.findCombinedSkillByNum(id, num);
            if (!s.isEmpty()) {
                List<Skill> temp = new ArrayList<>();
                for (Object[] objects : s)
                    temp.add(new Skill((String) objects[0], (String) objects[1]));
                return temp;
            }
            logger.warn("Skill not found with id {} or num {}",id,num);
            return null;
        }
        catch (PersistenceException | InvalidDataAccessResourceUsageException ex) {
            logger.warn("Invalid query at getCombinedSkillByNum with skill repository findCombinedSkillByNum");
            throw new PersistenceException();
        }
    }

    public Integer getShipIdByName(String name) {
        try {
            logger.info("Ship repository finding ship id by name {}", name);
            Optional<Integer> s = shipRepository.findShipIdByName(name);
            if (s.isPresent())
                return s.get();
            logger.warn("Ship id not found with name {}",name);
            return null;
        }
        catch (PersistenceException | InvalidDataAccessResourceUsageException ex) {
            logger.error("Invalid query at getShipIdByName with ship repository");
            throw new PersistenceException(); }
    }

    public Skin getSkinByShipId(int id) {
        try {
            logger.info("Skin repository get skin info by ship id {}", id);
            Optional<Skin> s = skinRepository.findSkinInfoByShipId(id);
            if (s.isPresent())
                return s.get();
            logger.warn("Skin not found with ship id {}",id);
            return null;
        }
        catch (PersistenceException | InvalidDataAccessResourceUsageException ex) {
            logger.error("Invalid query at findSkinInfoByShipId with skin repository");
            throw new PersistenceException(); }
    }

    public Integer getSkinIdByShipId(int id) {
        logger.info("Getting skin id by ship id, getSkinIdByShipId");
        Skin skin = getSkinByShipId(id);
        return (skin == null) ? null : skin.getId();
    }

    public String getShipNameById(int id) {
        try {
            logger.info("Ship repository finding ship name by id");
            Optional<String> s = shipRepository.findShipNameById(id);
            if (s.isPresent())
                return s.get();
            logger.warn("Ship name not found with id {}",id);
            return null;
        }
        catch (PersistenceException | InvalidDataAccessResourceUsageException ex) {
            logger.error("Invalid query at findShipNameById with ship repository");
            throw new PersistenceException(); }
    }

    public String getShipOneSkinURL(int id) {
        logger.info("Getting ship one skin URL by id {}",id);
        Skin skin = getSkinByShipId(id);
        String ship_name = getShipNameById(id);
        if (skin == null || ship_name == null)
            return null;
        return "static/images/skin_images/" + skin.getSkin_image_name()
                                            + ship_name.replace(' ', '_') + ".png";
    }

//    private String getShipOneSkin(int id) {
//        Optional<String> option_name = getShipNameById(id);
//        String[] image_url = new String[1];
//        option_name.ifPresent(value -> {
//            String ship_name = value.toLowerCase().replace(' ','_');
//            String basePath = "src/main/resources/static/images/skin_images";
//            String classPath = "static/images/skin_images/";
//            Path folder = Paths.get(basePath);
//            System.out.println(ship_name);
//            try {
//                Files.walkFileTree(folder, new HashSet<>(List.of(FileVisitOption.FOLLOW_LINKS)), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
//                    @Override
//                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                        String fileName = file.getFileName().toString();
//                        if (fileName.toLowerCase().contains(ship_name)) {
//                            image_url[0] = fileName;
//                            return FileVisitResult.TERMINATE;
//                        }
//                        return FileVisitResult.CONTINUE;
//                    }
//                    @Override
//                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
//                        System.err.println("Failed to access file: " + file.toString());
//                        return FileVisitResult.CONTINUE;
//                    }
//                });
//            } catch (IOException e) {
//                System.out.println("Fail traversing the skin folder");
//            }
//            System.out.println(image_url[0]);
//            image_url[0] = classPath+image_url[0];
//        });
//        return image_url[0];
//    };

    public List<Ship> getShipsByRarity(String rarity) {
        try {
            logger.info("Ship repository finding ship by rarity {}", rarity);
            List<Object[]> s =  shipRepository.findShipByRarity(rarity);
            if (!s.isEmpty()) {
                List<Ship> temp = new ArrayList<>();
                for (Object[] o : s) {
                    Ship ship = new Ship();
                    ship.setName((String)o[0]);
                    ship.setRarity_class(((String)o[1]).toLowerCase().replace(" ", "_"));
                    temp.add(ship);
                }
                return temp;
            }
            logger.warn("Ship not found with rarity {}",rarity);
            return null;
        }
        catch (PersistenceException | InvalidDataAccessResourceUsageException ex) {
            logger.error("Invalid query at findShipByRarity with ship repository");
            throw new PersistenceException(); }
    }

    private List<Ship> getShipsByClass(String[] classes) {
        try {
            logger.info("Ship repository finding ship by classes");
            List<Object[]> s = shipRepository.findShipByClassification(classes);
            if (!s.isEmpty()) {
                List<Ship> temp = new ArrayList<>();
                for (Object[] o : s) {
                    Ship ship = new Ship();
                    ship.setName((String)o[0]);
                    ship.setRarity_class(((String)o[1]).toLowerCase().replace(" ", "_"));
                    temp.add(ship);
                }
                return temp;
            }
            logger.warn("Ship not found with classes {}", (Object) classes);
            return null;
        }
        catch (PersistenceException | InvalidDataAccessResourceUsageException ex) {
            logger.error("Invalid query at findShipByClassification with ship repository");
            throw new PersistenceException(); }
    }

    public List<Ship> getShipsByClassGroup(String c) {
        logger.info("Getting ships by class, getShipListByClass");
        for (Classification cl : Classification.values())
            if (cl.getClassAbbreviation().equals(c)) {
                List<Ship> ship_info = getShipsByClass(cl.getTypes());
                if (ship_info != null)
                    return ship_info;
            }
        logger.warn("At getShipListByClass, ship not found with class {}",c);
        return null;
    }

}
