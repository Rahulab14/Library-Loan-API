package com.parkease.library;

import com.parkease.library.dto.LoanRequest;
import com.parkease.library.entity.Book;
import com.parkease.library.exception.ConflictException;
import com.parkease.library.repository.BookRepository;
import com.parkease.library.repository.LoanRepository;
import com.parkease.library.repository.ReservationRepository;
import com.parkease.library.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

```
@Mock
private LoanRepository loanRepository;

@Mock
private BookRepository bookRepository;

@Mock
private ReservationRepository reservationRepository;

@InjectMocks
private LoanService loanService;

@Test
void shouldRejectUnavailableBook() {

    Book book = Book.builder()
            .id(1L)
            .available(false)
            .dailyFineRate(BigDecimal.TEN)
            .build();

    when(bookRepository.findById(1L))
            .thenReturn(Optional.of(book));

    LoanRequest request = new LoanRequest();
    request.setBookId(1L);
    request.setBorrowerName("John");

    assertThrows(
            ConflictException.class,
            () -> loanService.borrowBook(request)
    );
}
```

}
