package com.barLoyalty.gateway.dto;

import com.barLoyalty.gateway.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeResponse {
    private Long id;
    private String username;
    private Role role;
    private Integer currentPoints;
}
