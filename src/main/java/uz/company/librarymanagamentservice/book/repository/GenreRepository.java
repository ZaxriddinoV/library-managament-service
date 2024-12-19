package uz.company.librarymanagamentservice.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.librarymanagamentservice.book.entity.GenreEntity;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity,Integer> {

    Optional<GenreEntity> findByTitle(String title);
}
