package com.parkease.library.service;

import com.parkease.library.dto.LoanRequest;
import com.parkease.library.dto.ReturnResponse;
import com.parkease.library.entity.Book;
import com.parkease.library.entity.Loan;
import com.parkease.library.exception.ConflictException;
import com.parkease.library.exception.ResourceNotFoundException;
import com.parkease.library.repository.BookRepository;
import com.parkease.library.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public Loan borrowBook(LoanRequest request) {

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        if (!book.getAvailable()) {
            throw new ConflictException("Book is currently unavailable");
        }

        long activeLoans =
                loanRepository.countActiveLoans(
                        request.getBorrowerName());

        if (activeLoans >= 3) {
            throw new ConflictException(
                    "Borrower cannot have more than 3 active loans");
        }

        book.setAvailable(false);

        Loan loan = Loan.builder()
                .book(book)
                .borrowerName(request.getBorrowerName())
                .loanDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .build();

        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public ReturnResponse returnBook(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loan not found"));

        loan.setReturnedDate(LocalDate.now());

        long overdueDays = Math.max(
                0,
                ChronoUnit.DAYS.between(
                        loan.getDueDate(),
                        LocalDate.now()
                )
        );

        BigDecimal fine =
                loan.getBook()
                        .getDailyFineRate()
                        .multiply(BigDecimal.valueOf(overdueDays));

        Book book = loan.getBook();

        book.setAvailable(true);

        bookRepository.save(book);
        loanRepository.save(loan);

        return new ReturnResponse(
                loan.getId(),
                fine
        );
    }
}