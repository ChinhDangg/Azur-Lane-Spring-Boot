package com.demo.ShipInfoModel.Repositories;

import com.demo.ShipInfoModel.InfoClass.ShipPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipImagePosRepository extends JpaRepository<ShipPosition, Integer> {
}
