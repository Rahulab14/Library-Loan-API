package com.parkease.library.controller;

import com.parkease.library.dto.ReservationRequest;
import com.parkease.library.entity.Loan;
import com.parkease.library.entity.Reservation;
import com.parkease.library.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {

```
private final ReservationService reservationService;

@PostMapping("/reservations")
public Object reserveBook(
        @RequestBody ReservationRequest request) {

    return reservationService
            .createReservation(request);
}

@GetMapping("/books/{id}/reservations")
public List<Reservation> getReservations(
        @PathVariable Long id) {

    return reservationService
            .getReservations(id);
}

@DeleteMapping("/reservations/{id}")
public void cancelReservation(
        @PathVariable Long id) {

    reservationService
            .cancelReservation(id);
}

@PostMapping("/reservations/{id}/borrow")
public Loan borrowReservation(
        @PathVariable Long id) {

    return reservationService
            .borrowReservation(id);
}
```

}
