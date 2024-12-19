package uz.company.librarymanagamentservice.book.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.book.dto.GenreDTO;
import uz.company.librarymanagamentservice.book.entity.GenreEntity;
import uz.company.librarymanagamentservice.book.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);

    @Autowired
    private GenreRepository genreRepository;

    public GenreEntity create(GenreDTO genre) {
        logger.info("Create Genre");
        Optional<GenreEntity> byTitle = genreRepository.findByTitle(genre.getGenre());
        if (byTitle.isEmpty()) {
            GenreEntity genreEntity = new GenreEntity();
            genreEntity.setTitle(genre.getGenre());
            return genreRepository.save(genreEntity);
        } else logger.error("Genre already exists" + genre.getGenre());
        throw new AppBadException("Genre already exists");
    }

    public List<GenreEntity> getAll() {
        logger.info("Get All Genres");
        return genreRepository.findAll();
    }

    public GenreEntity getById(Integer id) {
        logger.info("Get Genre by ID");
        Optional<GenreEntity> byId = genreRepository.findById(id);
        if (byId.isEmpty()) {
            logger.error("Genre not found" + id);
            throw new AppBadException("Genre not found");
        }
        return byId.get();
    }

    public GenreEntity update(Integer id, GenreDTO genre) {
        logger.info("Update Genre");
        Optional<GenreEntity> byId = genreRepository.findById(id);
        if (byId.isEmpty()) {
            logger.error("Genre does not exist" + id);
            throw new AppBadException("Genre does not exist");
        }
        GenreEntity genreEntity = byId.get();
        genreEntity.setTitle(genre.getGenre());
        genreRepository.save(genreEntity);
        return genreEntity;
    }

    public Boolean delete(Integer id) {
        logger.info("Delete Genre");
        Optional<GenreEntity> byId = genreRepository.findById(id);
        if (byId.isEmpty()) {
            logger.error("Genre does not exist" + id);
            throw new AppBadException("Genre does not exist");
        }
        genreRepository.delete(byId.get());
        return true;
    }
}
