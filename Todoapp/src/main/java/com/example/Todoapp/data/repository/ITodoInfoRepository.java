package com.example.Todoapp.data.repository;

import com.example.Todoapp.data.entity.TodoInfo;
import com.example.Todoapp.data.repository.CrudRepo.ICrudRepository;

public interface ITodoInfoRepository extends ICrudRepository<TodoInfo, Long> {
    Iterable<TodoInfo> findByMonthBetween(int start, int end);
    Iterable<TodoInfo> closestTodoDeadline(int todos);
    void deleteIdBetween(int start, int end);
    Iterable<TodoInfo> findByTitleLike(String title);
}
