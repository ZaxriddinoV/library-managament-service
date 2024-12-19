package uz.company.librarymanagamentservice.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.book.dto.GenreDTO;
import uz.company.librarymanagamentservice.book.service.GenreService;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody GenreDTO genre) {
        return ResponseEntity.ok(genreService.create(genre));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(genreService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(genreService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestParam Integer id, @RequestBody GenreDTO genre) {
        return ResponseEntity.ok(genreService.update(id, genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(genreService.delete(id));
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
