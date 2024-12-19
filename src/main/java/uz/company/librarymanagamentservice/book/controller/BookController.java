package uz.company.librarymanagamentservice.book.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.book.dto.BookDTO;
import uz.company.librarymanagamentservice.book.dto.BookFilterDTO;
import uz.company.librarymanagamentservice.book.dto.PageBookDTO;
import uz.company.librarymanagamentservice.book.service.BookService;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDTO book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }
    @GetMapping("/")
    private ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.pagination(page - 1, size));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @GetMapping("/{title}")
    private ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }
    @PutMapping("/{id}")
    private ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody BookDTO book) {
        return ResponseEntity.ok(bookService.updateBook(id,book));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<?> add(@RequestBody BookFilterDTO dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Page<PageBookDTO> filter = bookService.filter(dto, page-1, size);
        return ResponseEntity.ok(filter);
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
