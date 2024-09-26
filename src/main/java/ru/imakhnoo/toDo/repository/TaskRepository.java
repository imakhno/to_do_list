package ru.imakhnoo.toDo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.imakhnoo.toDo.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT task FROM Task task WHERE task.userId = :userId ORDER BY task.id DESC")
    List<Task> findAllByUserId(int userId);

    Optional<Task> findById(Long id);
}
