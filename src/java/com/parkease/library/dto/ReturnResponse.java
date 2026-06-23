package com.parkease.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ReturnResponse {

    private Long loanId;

    private BigDecimal fineAmount;
}