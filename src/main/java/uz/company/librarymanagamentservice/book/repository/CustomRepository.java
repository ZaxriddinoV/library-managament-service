package uz.company.librarymanagamentservice.book.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uz.company.librarymanagamentservice.book.dto.BookFilterDTO;
import uz.company.librarymanagamentservice.book.dto.FilterResultDTO;
import uz.company.librarymanagamentservice.book.entity.BookEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomRepository {

    @Autowired
    private EntityManager entityManager;

    public FilterResultDTO<BookEntity> filter(BookFilterDTO filter, int page, int size) {
        StringBuilder conditionBuilder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filter.getTitle() != null) {
            conditionBuilder.append("and s.title ilike :title ");
            params.put("title", "%" + filter.getTitle() + "%");
        }
        if (filter.getAuthorId() != null) {
            conditionBuilder.append("and s.authorId =:authorId ");
            params.put("author", filter.getAuthorId());
        }
        if (filter.getGenreId() != null) {
            conditionBuilder.append("and s.genreId =:genreId ");
            params.put("genre", filter.getGenreId());
        }

        StringBuilder selectBuilder = new StringBuilder("From BookEntity as s where 1=1 ");
        selectBuilder.append(conditionBuilder);

        StringBuilder countBuilder = new StringBuilder("select count(*) From BookEntity as s where 1=1 ");
        countBuilder.append(conditionBuilder);

        Query selectQuery = entityManager.createQuery(selectBuilder.toString(), BookEntity.class);
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        selectQuery.setFirstResult((page) * size); // Page
        selectQuery.setMaxResults(size); // Max size
        //
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            selectQuery.setParameter(entry.getKey(), entry.getValue());
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }
        //
        List<BookEntity> entityList = selectQuery.getResultList();
        Long totalCount = (Long) countQuery.getSingleResult();

        return new FilterResultDTO<>(entityList, totalCount);
    }


}