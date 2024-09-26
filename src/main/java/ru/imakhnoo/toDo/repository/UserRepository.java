package ru.imakhnoo.toDo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.imakhnoo.toDo.entity.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    boolean existsByUsername(String username);
    Optional<MyUser> findByUsername(String username);
}
