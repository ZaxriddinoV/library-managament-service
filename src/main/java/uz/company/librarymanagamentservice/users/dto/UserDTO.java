package uz.company.librarymanagamentservice.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    @NotBlank(message = "The title must not be empty.")
    private String name;
    @NotBlank(message = "The email must not be empty.")
    private String email;
    @NotBlank(message = "The phone must not be empty.")
    private String phone;
}
