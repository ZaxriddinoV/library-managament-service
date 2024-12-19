package uz.company.librarymanagamentservice.loanManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanBookDTO {
    private Integer memberId;
    private Integer bookId;
    private Integer dueDate;
    private Integer availableCount;
}
