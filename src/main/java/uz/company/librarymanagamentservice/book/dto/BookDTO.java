package uz.company.librarymanagamentservice.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Integer id;
    @NotBlank(message = "The title must not be empty.")
    private String title;
    @NotNull
    private Integer authorId;
    @NotNull
    private Integer genreId;
    private Integer count;
}
