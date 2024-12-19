package uz.company.librarymanagamentservice.librarian.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private String username;
    private String JwtToken;
    private String RefreshToken;
}
