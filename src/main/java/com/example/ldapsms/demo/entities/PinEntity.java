package com.example.ldapsms.demo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "pin")
@Entity
@Setter
@Getter
@ToString
public class PinEntity {

    @Id
    @Column(name = "mobile", nullable = false)
    private Long mobile;

    private String pin;

    private Timestamp timestamp;

}