package com.parkease.library.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookRequest {

    private String title;

    private String author;

    private String isbn;

    private BigDecimal dailyFineRate;
}