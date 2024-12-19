package uz.company.librarymanagamentservice.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    private Integer id;
    @NotBlank(message = "The first name must not be empty.")
    private String firstName;
    @NotBlank(message = "The last name must not be empty.")
    private String lastName;
}
