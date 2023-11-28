package com.demo.ShipInfoModel.Repositories;

import com.demo.ShipInfoModel.InfoClass.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository<S extends Stats, Integer> extends JpaRepository<S, Integer> {
}
