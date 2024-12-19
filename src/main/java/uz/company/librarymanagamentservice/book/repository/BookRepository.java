package uz.company.librarymanagamentservice.book.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uz.company.librarymanagamentservice.book.entity.BookAuthorEntity;
import uz.company.librarymanagamentservice.book.entity.BookEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer>, PagingAndSortingRepository<BookEntity,Integer> {
    Optional<BookEntity> findByIdAndVisibleTrue(Integer id);

    @Query("FROM BookEntity s WHERE s.visible = TRUE")
    Page<BookEntity> getAllBook(PageRequest pageRequest);

    List<BookEntity> findByTitle(String title);

    BookEntity findByAuthor(List<BookAuthorEntity> author);
}
