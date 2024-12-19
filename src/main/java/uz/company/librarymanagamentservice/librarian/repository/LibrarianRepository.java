package uz.company.librarymanagamentservice.librarian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.librarymanagamentservice.librarian.entity.LibrarianEntity;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<LibrarianEntity,Integer> {


    Optional<LibrarianEntity> findByUsername(String username);
}
