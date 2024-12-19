package uz.company.librarymanagamentservice.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookFilterDTO {
    private String title;
    private Integer authorId;
    private Integer genreId;
}
