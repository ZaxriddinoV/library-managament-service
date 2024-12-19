package uz.company.librarymanagamentservice.securityConfig.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.company.librarymanagamentservice.securityConfig.config.CustomUserDetails;
import uz.company.librarymanagamentservice.librarian.entity.LibrarianEntity;
import uz.company.librarymanagamentservice.librarian.repository.LibrarianRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private LibrarianRepository librarianRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LibrarianEntity> optional = librarianRepository.findByUsername(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        LibrarianEntity profile = optional.get();
        return new CustomUserDetails(profile) ;
    }

}
