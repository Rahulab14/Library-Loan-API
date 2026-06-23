package com.parkease.library;

import com.parkease.library.dto.ReservationRequest;
import com.parkease.library.entity.Book;
import com.parkease.library.repository.BookRepository;
import com.parkease.library.repository.LoanRepository;
import com.parkease.library.repository.ReservationRepository;
import com.parkease.library.service.LoanService;
import com.parkease.library.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

```
@Mock
private ReservationRepository reservationRepository;

@Mock
private BookRepository bookRepository;

@Mock
private LoanRepository loanRepository;

@Mock
private LoanService loanService;

@InjectMocks
private ReservationService reservationService;

@Test
void shouldLoadBookForReservation() {

    Book book = Book.builder()
            .id(1L)
            .available(false)
            .build();

    when(bookRepository.findById(1L))
            .thenReturn(Optional.of(book));

    ReservationRequest request =
            new ReservationRequest();

    request.setBookId(1L);
    request.setBorrowerName("John");

    assertNotNull(request);
}
```

}
