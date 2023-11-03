package com.demo.BasicInfo.Repositories;

import com.demo.BasicInfo.Info.GearSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GearSlotRepository extends JpaRepository<GearSlot, Integer> {

}
