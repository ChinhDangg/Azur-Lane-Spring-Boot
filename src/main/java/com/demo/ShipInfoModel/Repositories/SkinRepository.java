package com.demo.ShipInfoModel.Repositories;

import com.demo.ShipInfoModel.InfoClass.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SkinRepository extends JpaRepository<Skin, Integer> {

    @Query(value = "SELECT * FROM skins WHERE ship_id = :ship_id LIMIT 1", nativeQuery = true)
    Optional<Skin> findSkinInfoByShipId(@Param("ship_id") int ship_id);
}
