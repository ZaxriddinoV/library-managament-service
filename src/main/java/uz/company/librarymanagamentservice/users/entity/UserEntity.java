package uz.company.librarymanagamentservice.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.company.librarymanagamentservice.librarian.entity.LibrarianEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "membership_date")
    private LocalDate membershipDate;
    @Column(name = "created_by_id")
    private Integer createdById;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id",insertable = false,updatable = false)
    private LibrarianEntity librarian;
    private Boolean visible;

}
