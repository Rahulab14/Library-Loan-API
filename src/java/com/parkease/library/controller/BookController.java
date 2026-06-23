package com.parkease.library.controller;

import com.parkease.library.dto.BookRequest;
import com.parkease.library.dto.FineUpdateRequest;
import com.parkease.library.entity.Book;
import com.parkease.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public Book createBook(
            @RequestBody BookRequest request) {

        return bookService.createBook(request);
    }

    @GetMapping
    public List<Book> getBooks(
            @RequestParam(required = false)
            Boolean available) {

        return bookService.getAllBooks(available);
    }

    @PutMapping("/{id}/fine")
    public Book updateFine(
            @PathVariable Long id,
            @RequestBody FineUpdateRequest request) {

        return bookService.updateFineRate(
                id,
                request.getDailyFineRate()
        );
    }
}