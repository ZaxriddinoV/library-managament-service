package uz.company.librarymanagamentservice.book.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.book.dto.AuthorDTO;
import uz.company.librarymanagamentservice.book.entity.BookAuthorEntity;
import uz.company.librarymanagamentservice.book.repository.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorDTO add(AuthorDTO dto) {
        logger.info("Adding author: {}", dto.getFirstName());
        BookAuthorEntity bookAuthorEntity = new BookAuthorEntity();
        bookAuthorEntity.setFirstName(dto.getFirstName());
        bookAuthorEntity.setLastName(dto.getLastName());
        bookAuthorEntity.setVisible(Boolean.TRUE);
        authorRepository.save(bookAuthorEntity);
        dto.setId(bookAuthorEntity.getId());
        return dto;
    }

    public AuthorDTO update(Integer id, AuthorDTO dto) {
        logger.info("Updating author: {}", dto.getFirstName());
        Optional<BookAuthorEntity> byId = authorRepository.findByIdAndVisibleTrue(id);
        if (byId.isPresent()) {
            BookAuthorEntity bookAuthorEntity = byId.get();
            bookAuthorEntity.setFirstName(dto.getFirstName());
            bookAuthorEntity.setLastName(dto.getLastName());
            bookAuthorEntity.setVisible(Boolean.TRUE);
            authorRepository.save(bookAuthorEntity);
            dto.setId(bookAuthorEntity.getId());
            return dto;
        } else {
            logger.error("Author not found{}", dto.getFirstName());
            throw new AppBadException("Author not found");
        }
    }

    public Boolean delete(Integer id) {
        Optional<BookAuthorEntity> byIdAndVisibleTrue = authorRepository.findByIdAndVisibleTrue(id);
        if (byIdAndVisibleTrue.isPresent()) {
            BookAuthorEntity bookAuthorEntity = byIdAndVisibleTrue.get();
            bookAuthorEntity.setVisible(Boolean.FALSE);
            authorRepository.save(bookAuthorEntity);
            logger.info("Deleting author: {}", id);
            return true;
        } else {logger.error("Author not found{}", id); throw new AppBadException("Author not found");}
    }


    public AuthorDTO getById(Integer id) {
        logger.info("Retrieving author: {}", id);
        Optional<BookAuthorEntity> byIdAndVisibleTrue = authorRepository.findByIdAndVisibleTrue(id);
        if (byIdAndVisibleTrue.isPresent()) {
            BookAuthorEntity bookAuthorEntity = byIdAndVisibleTrue.get();
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(bookAuthorEntity.getId());
            authorDTO.setFirstName(bookAuthorEntity.getFirstName());
            authorDTO.setLastName(bookAuthorEntity.getLastName());
            return authorDTO;
        } else throw new AppBadException("Author not found");
    }
}
