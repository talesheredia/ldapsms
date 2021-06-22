package com.example.ldapsms.demo.entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoEntityRepository extends JpaRepository<AcessoEntity, Long> {

    AcessoEntity findFirstByMobileOrderByIdDesc(Long mobile);

}