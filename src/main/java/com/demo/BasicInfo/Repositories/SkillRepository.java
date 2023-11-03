package com.demo.BasicInfo.Repositories;

import com.demo.BasicInfo.Info.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skills, Integer> {
    @Query(value = "SELECT * FROM skills WHERE ship_id = ?1", nativeQuery = true)
    List<Skills> findSkillByShipId(Integer id);

    @Query(value = "SELECT skill_name, string_agg(skill_description, E'\\n\\n') AS skill_description, skill_num " +
            "FROM skills " +
            "WHERE ship_id = :id " +
            "GROUP BY skill_name, ship_id, skill_num " +
            "ORDER BY skill_num", nativeQuery = true)
    List<Object[]> findCombinedSkill(@Param("id") int id);

    @Query(value = "SELECT skill_name, string_agg(skill_description, E'\\n\\n') AS skill_description " +
            "FROM skills " +
            "WHERE ship_id = :id AND skill_num = :num " +
            "GROUP BY skill_name, ship_id " +
            "ORDER BY ship_id", nativeQuery = true)
    List<Object[]> findCombinedSkillByNum(@Param("id") int id, @Param("num") int num);


}
