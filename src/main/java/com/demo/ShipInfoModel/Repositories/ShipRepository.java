package com.demo.ShipInfoModel.Repositories;

import com.demo.ShipInfoModel.InfoClass.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship, Integer> {

    @Query(value = "SELECT ship_name, rarity FROM ships WHERE rarity = :rarity", nativeQuery = true)
    List<Object[]> findShipByRarity(@Param("rarity") String rarity);

    @Query(value = "SELECT ship_name, rarity FROM ships ORDER BY CASE WHEN rarity = 'Ultra Rare' THEN 1 " +
                                                                     "WHEN rarity = 'Super Rare' THEN 2 " +
                                                                     "WHEN rarity = 'Elite' THEN 3 " +
                                                                     "WHEN rarity = 'Rare' THEN 4 ELSE 5 END;", nativeQuery = true)
    List<Object[]> findAllShipByRarity();

    @Query(value = "SELECT ship_name, rarity FROM ships WHERE classification IN :type", nativeQuery = true)
    List<Object[]> findShipByClassification(@Param("type")String[] type);

    @Query(value = "SELECT id FROM ships WHERE ship_name = :name", nativeQuery = true)
    Optional<Integer> findShipIdByName(@Param("name") String name);

    @Query(value = "SELECT ship_name FROM ships WHERE id = :id", nativeQuery = true)
    Optional<String> findShipNameById(@Param("id") int id);

}
