package com.demo.BasicInfo.Repositories;

import com.demo.BasicInfo.Info.Ships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ships, Integer> {

    @Query(value = "SELECT ship_name, rarity FROM ships WHERE rarity = 'Ultra Rare'", nativeQuery = true)
    List<Object[]> getUltraRareShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE rarity = 'Super Rare'", nativeQuery = true)
    List<Object[]> getSuperRareShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE rarity = 'Elite'", nativeQuery = true)
    List<Object[]> getEliteShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE rarity = 'Rare'", nativeQuery = true)
    List<Object[]> getRareShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE rarity = 'Normal'", nativeQuery = true)
    List<Object[]> getNormalShip();
    @Query(value = "SELECT ship_name, rarity FROM ships ORDER BY rarity DESC", nativeQuery = true)
    List<Object[]> getAllShipByRarity();

    @Query(value = "SELECT ship_name, rarity FROM ships WHERE classification in ('Battleship', 'Battlecruiser', 'Aviation Battleship', 'Monitor')", nativeQuery = true)
    List<Object[]> getBBShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE classification in ('Aircraft Carrier', 'Light Aircraft Carrier')", nativeQuery = true)
    List<Object[]> getCVShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE classification in ('Heavy Cruiser', 'Large Cruiser')", nativeQuery = true)
    List<Object[]> getCAShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE classification = 'Light Cruiser'", nativeQuery = true)
    List<Object[]> getCLShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE classification in ('Destroyer', 'Guided-missile Destroyer')", nativeQuery = true)
    List<Object[]> getDDShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE classification in ('Submarine', 'Submarine Carrier', 'Sailing Frigate')", nativeQuery = true)
    List<Object[]> getSSShip();
    @Query(value = "SELECT ship_name, rarity FROM ships WHERE classification in ('Munition Ship', 'Repair Ship')", nativeQuery = true)
    List<Object[]> getOtherShip();

    @Query(value = "SELECT id FROM ships WHERE ship_name = :name", nativeQuery = true)
    Integer getShipIdByName(@Param("name") String name);

}
