package uz.company.librarymanagamentservice.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.book.dto.*;
import uz.company.librarymanagamentservice.book.entity.BookEntity;
import uz.company.librarymanagamentservice.book.entity.GenreEntity;
import uz.company.librarymanagamentservice.book.repository.BookRepository;
import uz.company.librarymanagamentservice.book.repository.CustomRepository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CustomRepository customRepository;

    public BookDTO addBook(BookDTO bookDTO) {
        GenreEntity genre = genreService.getById(bookDTO.getGenreId());
        AuthorDTO author = authorService.getById(bookDTO.getAuthorId());
        BookEntity bookEntity = new BookEntity();
        bookEntity.setGenreId(genre.getId());
        bookEntity.setAuthorId(author.getId());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setCount(bookDTO.getCount());
        bookEntity.setCreatedDate(LocalDate.now());
        bookEntity.setVisible(Boolean.TRUE);
        bookRepository.save(bookEntity);

        return bookDTO;
    }
    public Page<PageBookDTO> pagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<BookEntity> entityList = bookRepository.getAllBook(pageRequest);
        long total = entityList.getTotalElements();
        List<PageBookDTO> dtoList = new LinkedList<>();
        for (BookEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, pageRequest, total);
    }
    private PageBookDTO toDTO(BookEntity entity) {
        PageBookDTO dto = new PageBookDTO();
        dto.setGenreId(entity.getId());
        dto.setAuthorId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setCount(entity.getCount());
        dto.setCreationDate(LocalDate.now());
        return dto;
    }
    public PageBookDTO getBookById(Integer id) {
        Optional<BookEntity> bookEntity = bookRepository.findByIdAndVisibleTrue(id);
        if (bookEntity.isPresent()) {
            return toDTO(bookEntity.get());
        }else throw new AppBadException("Book not found");
    }
    public List<PageBookDTO> getBookByTitle(String title) {
        List<BookEntity> byTitle = bookRepository.findByTitle(title);
        List<PageBookDTO> dtoList = new LinkedList<>();
        if (byTitle.isEmpty()) {
            throw new AppBadException("Book not found");
        }else{
            for (BookEntity entity : byTitle) {
                dtoList.add(toDTO(entity));
            }
            return dtoList;
        }
    }


    public BookDTO updateBook(Integer id,BookDTO bookDTO) {
        Optional<BookEntity> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            BookEntity bookEntity = byId.get();
            bookEntity.setTitle(bookDTO.getTitle());
            bookEntity.setCount(bookDTO.getCount());
            bookEntity.setGenreId(bookDTO.getGenreId());
            bookEntity.setAuthorId(bookDTO.getAuthorId());
            bookEntity.setCreatedDate(LocalDate.now());
            bookRepository.save(bookEntity);
            return bookDTO;
        }else throw new AppBadException("Book not found");
    }
    public Boolean deleteBook(Integer id) {
        Optional<BookEntity> byIdAndVisibleTrue = bookRepository.findByIdAndVisibleTrue(id);
        if (byIdAndVisibleTrue.isPresent()) {
            byIdAndVisibleTrue.get().setVisible(Boolean.FALSE);
            bookRepository.save(byIdAndVisibleTrue.get());
            return true;
        }else throw new AppBadException("Book not found");
    }


    public Page<PageBookDTO> filter(BookFilterDTO filter, int page, int size) {

        FilterResultDTO<BookEntity> result = customRepository.filter(filter, page, size);
        List<PageBookDTO> dtoList = new LinkedList<>();
        for (BookEntity entity : result.getContent()) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, PageRequest.of(page, size), result.getTotal());
    }
}
