package com.parkease.library.service;

import com.parkease.library.dto.BookRequest;
import com.parkease.library.entity.Book;
import com.parkease.library.exception.ConflictException;
import com.parkease.library.exception.ResourceNotFoundException;
import com.parkease.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(BookRequest request) {

        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new ConflictException("ISBN already exists");
        }

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .dailyFineRate(
                        request.getDailyFineRate() == null
                                ? BigDecimal.TEN
                                : request.getDailyFineRate()
                )
                .available(true)
                .build();

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(Boolean available) {

        if (available == null) {
            return bookRepository.findAll();
        }

        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getAvailable().equals(available))
                .toList();
    }

    public Book updateFineRate(Long id, BigDecimal fineRate) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        book.setDailyFineRate(fineRate);

        return bookRepository.save(book);
    }
}