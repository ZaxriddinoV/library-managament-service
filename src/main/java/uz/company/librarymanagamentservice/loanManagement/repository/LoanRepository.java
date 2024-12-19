package uz.company.librarymanagamentservice.loanManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.librarymanagamentservice.loanManagement.entity.LoanEntity;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity,Integer> {

    List<LoanEntity> findByBookIdAndReturnDateIsNull(Integer bookId);
}
