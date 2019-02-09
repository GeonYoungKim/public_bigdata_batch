package com.skuniv.bigdata.repository;

import com.skuniv.bigdata.domain.entity.BargainDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BargainDateRepository extends JpaRepository<BargainDate, Long> {
}
