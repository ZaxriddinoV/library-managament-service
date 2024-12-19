package uz.company.librarymanagamentservice.securityConfig.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.company.librarymanagamentservice.librarian.entity.LibrarianEntity;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private Integer id;
    private String workTime;
    private String username;
    private String password;
    private String email;

    public CustomUserDetails(LibrarianEntity profile) {
        this.id = profile.getId();
        this.workTime = profile.getWorkTime();
        this.username = profile.getUsername();
        this.password = profile.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
