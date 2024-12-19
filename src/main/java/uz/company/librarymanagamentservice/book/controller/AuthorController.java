package uz.company.librarymanagamentservice.book.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.book.dto.AuthorDTO;
import uz.company.librarymanagamentservice.book.service.AuthorService;


@RestController
@RequestMapping("/api/v1/book-author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/")
    public ResponseEntity<?> addAuthor(@Valid @RequestBody AuthorDTO dto) {
        return ResponseEntity.ok(authorService.add(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllAuthors(@PathVariable Integer id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id,@Valid @RequestBody AuthorDTO dto) {
        return ResponseEntity.ok(authorService.update(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer id) {
        return ResponseEntity.ok(authorService.delete(id));
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
