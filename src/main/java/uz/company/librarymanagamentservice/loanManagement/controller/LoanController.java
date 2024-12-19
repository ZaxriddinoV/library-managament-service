package uz.company.librarymanagamentservice.loanManagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.loanManagement.dto.LoanBookDTO;
import uz.company.librarymanagamentservice.loanManagement.service.LoanService;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@Valid @RequestBody LoanBookDTO book) {
        return ResponseEntity.ok(loanService.issueBook(book));
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Integer id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
