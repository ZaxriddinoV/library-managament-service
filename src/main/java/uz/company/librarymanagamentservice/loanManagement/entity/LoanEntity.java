package uz.company.librarymanagamentservice.loanManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.company.librarymanagamentservice.loanManagement.enums.LoansRole;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "loans")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "member_id")
    private Integer memberId;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private LoansRole role;
}
