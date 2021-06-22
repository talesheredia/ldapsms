package com.example.ldapsms.demo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "Acesso")
@Entity
@Getter
@Setter
@ToString
public class AcessoEntity {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String fullName;
    private String email;
    private Long mobile;

    //IpInfo
    private String status;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private double lat;
    private double lon;
    private String timezone;
    private String isp;
    private String org;
    private String as;
    private String query;

    //DeviceInfo
    private String userAgent;
    private String os;
    private String browser;
    private String device;
    private String osVersion;
    private String browserVersion;
    private String deviceType;
    private String orientation;

    //pin
    private String pin;

    //AD
    private String usuarioAD;
    private String passwordAD;

}
