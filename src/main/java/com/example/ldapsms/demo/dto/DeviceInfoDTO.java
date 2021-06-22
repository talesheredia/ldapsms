package com.example.ldapsms.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeviceInfoDTO {
    private String userAgent;
    private String os;
    private String browser;
    private String device;
    @JsonProperty("os_version")
    private String osVersion;
    @JsonProperty("browser_version")
    private String browserVersion;
    private String deviceType;
    private String orientation;
}
