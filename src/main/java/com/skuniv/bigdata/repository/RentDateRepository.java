package com.skuniv.bigdata.repository;

import com.skuniv.bigdata.domain.entity.RentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDateRepository extends JpaRepository<RentDate,Long> {
}
