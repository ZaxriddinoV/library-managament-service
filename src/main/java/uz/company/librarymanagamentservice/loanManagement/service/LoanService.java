package uz.company.librarymanagamentservice.loanManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.company.librarymanagamentservice.book.dto.PageBookDTO;
import uz.company.librarymanagamentservice.book.service.BookService;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.loanManagement.dto.LoanBookDTO;
import uz.company.librarymanagamentservice.loanManagement.entity.LoanEntity;
import uz.company.librarymanagamentservice.loanManagement.enums.LoansRole;
import uz.company.librarymanagamentservice.loanManagement.repository.LoanRepository;
import uz.company.librarymanagamentservice.users.dto.UserDTO;
import uz.company.librarymanagamentservice.users.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    public String issueBook(LoanBookDTO dto) {
        List<LoanEntity> activeLoans = loanRepository.findByBookIdAndReturnDateIsNull(dto.getBookId());
        if (activeLoans.size() >= dto.getAvailableCount()) {
            logger.info("The book is not available, all copies are on rent!{}", dto);
            return "The book is not available, all copies are on rent!";
        }
        UserDTO userById = userService.getUserById(dto.getMemberId());
        PageBookDTO bookById = bookService.getBookById(dto.getBookId());
        if (bookById == null) {throw new AppBadException("The book is not available");}
        if (userById == null) {throw new AppBadException("The book is not available");}
        LoanEntity loan = new LoanEntity();
        loan.setMemberId(dto.getMemberId());
        loan.setBookId(dto.getBookId());
        loan.setIssueDate(LocalDate.now());
        loan.setRole(LoansRole.ROLE_ISSUED);
        loan.setDueDate(LocalDate.now().plusDays(dto.getDueDate()));
        loanRepository.save(loan);
        return "The book was successfully rented!";
    }
    public String returnBook(Integer loanId) {
        LoanEntity loan = loanRepository.findById(loanId).orElseThrow(() -> new AppBadException("Rental not found!"));
        if (loan.getReturnDate() != null) {
            logger.info("The book has already been returned!{}", loanId);
            return "The book has already been returned!";
        }
        loan.setReturnDate(LocalDate.now());
        loan.setRole(LoansRole.ROLE_RETURNED);
        loanRepository.save(loan);
        logger.info("The book was successfully returned!{}", loanId);
        return "The book was successfully returned!";
    }

    public List<LoanEntity> getAllLoans() {
        return loanRepository.findAll();
    }
}
