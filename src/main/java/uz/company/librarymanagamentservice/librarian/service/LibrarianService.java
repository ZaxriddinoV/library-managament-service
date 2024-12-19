package uz.company.librarymanagamentservice.librarian.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.company.librarymanagamentservice.book.service.GenreService;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.librarian.dto.AuthDTO;
import uz.company.librarymanagamentservice.librarian.dto.ProfileDTO;
import uz.company.librarymanagamentservice.librarian.entity.LibrarianEntity;
import uz.company.librarymanagamentservice.librarian.repository.LibrarianRepository;
import uz.company.librarymanagamentservice.util.JwtUtil;
import uz.company.librarymanagamentservice.util.MD5Util;

import java.util.Optional;

@Service
public class LibrarianService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);
    @Autowired
    private LibrarianRepository librarianRepository;

    public ProfileDTO login(AuthDTO dto) {
        logger.info("Login User{}", dto.getUsername());
        Optional<LibrarianEntity> optional = librarianRepository.findByUsername(dto.getUsername());
        if (optional.isEmpty()) {
            logger.error("Email or Password wrong{}", dto.getUsername());
            throw new AppBadException("Email or Password wrong");
        }
        LibrarianEntity entity = optional.get();
        if (!entity.getPassword().equals(MD5Util.md5(dto.getPassword()))) {
            logger.error("Wrong password");
            throw new AppBadException("Email or Password wrong");
        }else {
            logger.info("Login Success");
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setUsername(entity.getUsername());
            profileDTO.setJwtToken(JwtUtil.encode(entity.getUsername(), entity.getWorkTime()));
            profileDTO.setRefreshToken(JwtUtil.generateRefreshToken(entity.getUsername()));
            return profileDTO;
        }
    }

    public LibrarianEntity register(AuthDTO authDTO) {
        logger.info("Register User{}", authDTO.getUsername());
        Optional<LibrarianEntity> byUsername = librarianRepository.findByUsername(authDTO.getUsername());
        if (byUsername.isEmpty()) {
            LibrarianEntity entity = new LibrarianEntity();
            entity.setUsername(authDTO.getUsername());
            entity.setPassword(MD5Util.md5(authDTO.getPassword()));
            librarianRepository.save(entity);
            return entity;
        } else logger.error("Username already exist{}", authDTO.getUsername()); throw new AppBadException("Username already exist");
    }
}
