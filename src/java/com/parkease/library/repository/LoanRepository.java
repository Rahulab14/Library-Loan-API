package com.parkease.library.repository;

import com.parkease.library.entity.Book;
import com.parkease.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

```
@Query("""
       SELECT COUNT(l)
       FROM Loan l
       WHERE l.borrowerName = :borrowerName
       AND l.returnedDate IS NULL
       """)
long countActiveLoans(String borrowerName);

List<Loan> findByBorrowerNameOrderByLoanDateDesc(
        String borrowerName
);

List<Loan> findByReturnedDateIsNullAndDueDateBefore(
        LocalDate date
);

boolean existsByBookAndBorrowerNameAndReturnedDateIsNull(
        Book book,
        String borrowerName
);
```

}
