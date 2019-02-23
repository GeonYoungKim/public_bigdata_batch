package com.skuniv.bigdata.repository;

import com.skuniv.bigdata.domain.entity.BargainDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BargainDateRepository extends JpaRepository<BargainDate, Long> {
}
