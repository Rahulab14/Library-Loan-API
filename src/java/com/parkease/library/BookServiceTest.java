package com.parkease.library;

import com.parkease.library.dto.BookRequest;
import com.parkease.library.entity.Book;
import com.parkease.library.exception.ConflictException;
import com.parkease.library.repository.BookRepository;
import com.parkease.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

```
@Mock
private BookRepository repository;

@InjectMocks
private BookService service;

@Test
void shouldCreateBook() {

    BookRequest request = new BookRequest();
    request.setTitle("Spring Boot");
    request.setAuthor("John");
    request.setIsbn("ISBN001");
    request.setDailyFineRate(BigDecimal.TEN);

    when(repository.existsByIsbn("ISBN001"))
            .thenReturn(false);

    Book book = Book.builder()
            .id(1L)
            .title("Spring Boot")
            .build();

    when(repository.save(any(Book.class)))
            .thenReturn(book);

    Book result = service.createBook(request);

    assertNotNull(result);
}

@Test
void shouldRejectDuplicateIsbn() {

    BookRequest request = new BookRequest();
    request.setIsbn("ISBN001");

    when(repository.existsByIsbn("ISBN001"))
            .thenReturn(true);

    assertThrows(
            ConflictException.class,
            () -> service.createBook(request)
    );
}
```

}
