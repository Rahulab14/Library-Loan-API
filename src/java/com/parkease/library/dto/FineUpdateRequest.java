package com.parkease.library.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FineUpdateRequest {

    private BigDecimal dailyFineRate;
}