package com.skuniv.bigdata.repository;

import com.skuniv.bigdata.domain.entity.CharterDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharterDateRepository extends JpaRepository<CharterDate, Long> {
}
