package com.example.Todoapp.repository;

import com.example.Todoapp.entity.TodoInfo;
import com.example.Todoapp.repository.CrudRepo.ICrudRepository;

public interface ITodoInfoRepository extends ICrudRepository<TodoInfo, Long> {
    Iterable<TodoInfo> findByMonthBetween(int start, int end);
    Iterable<TodoInfo> closestTodoDeadline(int todos);
}
