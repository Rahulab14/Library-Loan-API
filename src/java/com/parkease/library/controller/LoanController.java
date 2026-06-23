package com.parkease.library.controller;

import com.parkease.library.dto.LoanRequest;
import com.parkease.library.dto.ReturnResponse;
import com.parkease.library.entity.Loan;
import com.parkease.library.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public Loan borrowBook(
            @RequestBody LoanRequest request) {

        return loanService.borrowBook(request);
    }

    @PutMapping("/{id}/return")
    public ReturnResponse returnBook(
            @PathVariable Long id) {

        return loanService.returnBook(id);
    }
}