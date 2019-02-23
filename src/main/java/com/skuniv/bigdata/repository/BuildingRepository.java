package com.skuniv.bigdata.repository;

import com.skuniv.bigdata.domain.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building findByCityAndGroopAndBuildingNumAndFloor(Integer city, Integer groop, String buildingNum, Integer floor);
}