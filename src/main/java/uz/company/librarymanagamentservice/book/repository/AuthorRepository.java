package uz.company.librarymanagamentservice.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.company.librarymanagamentservice.book.entity.BookAuthorEntity;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<BookAuthorEntity, Integer> {
    Optional<BookAuthorEntity> findByIdAndVisibleTrue(Integer id);
}
