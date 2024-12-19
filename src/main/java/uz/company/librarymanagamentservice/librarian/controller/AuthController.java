package uz.company.librarymanagamentservice.librarian.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.librarian.dto.AuthDTO;
import uz.company.librarymanagamentservice.librarian.dto.ProfileDTO;
import uz.company.librarymanagamentservice.librarian.service.LibrarianService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    LibrarianService service;

    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDTO authDTO ){
        ProfileDTO login = service.login(authDTO);
        return ResponseEntity.ok(login);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthDTO authDTO){
        return ResponseEntity.ok(service.register(authDTO));
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
