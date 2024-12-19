package uz.company.librarymanagamentservice.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;

    @Column(name = "author_id")
    private Integer authorId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id") )
    private List<BookAuthorEntity> author;

    @Column(name = "genere_id")
    private Integer genreId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genere_id",insertable = false,updatable = false)
    private GenreEntity genre;
    @Column(name = "Created_date")
    private LocalDate createdDate;

    @Column(name = "count")
    private Integer count;
    @Column(name = "visible")
    private Boolean visible;
}
