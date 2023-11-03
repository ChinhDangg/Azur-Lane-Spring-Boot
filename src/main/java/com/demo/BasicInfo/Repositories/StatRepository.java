package com.demo.BasicInfo.Repositories;

import com.demo.BasicInfo.Info.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository<S extends Stats, Integer> extends JpaRepository<S, Integer> {
}
