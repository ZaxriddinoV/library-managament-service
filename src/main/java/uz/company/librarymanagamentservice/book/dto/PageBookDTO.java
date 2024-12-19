package uz.company.librarymanagamentservice.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PageBookDTO {
    private String title;
    private Integer authorId;
    private Integer genreId;
    private LocalDate creationDate;
    private Integer count;
}
