package com.skuniv.bigdata.repository;

import com.skuniv.bigdata.domain.entity.RentDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentDateRepository extends JpaRepository<RentDate, Long> {
}
