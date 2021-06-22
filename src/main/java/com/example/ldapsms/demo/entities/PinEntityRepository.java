package com.example.ldapsms.demo.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PinEntityRepository extends JpaRepository<PinEntity, Long> {

    Optional<PinEntity> findByMobileAndTimestampAfter(Long id, Date timestamp);
}