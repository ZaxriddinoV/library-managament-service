package uz.company.librarymanagamentservice.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.company.librarymanagamentservice.users.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("FROM UserEntity AS u WHERE u.visible = TRUE ")
    List<UserEntity> getAll();

    Optional<UserEntity> findByIdAndVisibleTrue(Integer id);
    Optional<UserEntity> findByEmailAndVisibleTrue(String email);
    Optional<UserEntity> findByPhoneAndVisibleTrue(String phone);

}
