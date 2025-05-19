package com.alibarandemir.isin_asli_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDto {
    private long totalUsers;
    private long activeUsers;
    private long totalCompanies;
    private long activeCompanies;
} 