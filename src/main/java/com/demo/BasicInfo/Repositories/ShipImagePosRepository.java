package com.demo.BasicInfo.Repositories;

import com.demo.BasicInfo.Info.ShipPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipImagePosRepository extends JpaRepository<ShipPosition, Integer> {
}
