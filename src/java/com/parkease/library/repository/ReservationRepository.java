package com.parkease.library.repository;

import com.parkease.library.entity.Book;
import com.parkease.library.entity.Reservation;
import com.parkease.library.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository
extends JpaRepository<Reservation, Long> {

```
List<Reservation> findByBookAndStatusOrderByReservedAtAsc(
        Book book,
        ReservationStatus status
);

long countByBorrowerNameAndStatusIn(
        String borrowerName,
        List<ReservationStatus> statuses
);

boolean existsByBookAndBorrowerNameAndStatusIn(
        Book book,
        String borrowerName,
        List<ReservationStatus> statuses
);
```

}
