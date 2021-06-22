package com.example.ldapsms.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RootDTO {

    @JsonProperty("full_name")
    private String fullName;
    private String email;
    private String cpf;
    private Long mobile;
    @JsonProperty("ip_info")
    private IpInfoDTO ipInfo;
    @JsonProperty("device_info")
    private DeviceInfoDTO deviceInfo;


}
