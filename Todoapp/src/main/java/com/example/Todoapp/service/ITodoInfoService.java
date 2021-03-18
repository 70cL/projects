package com.example.Todoapp.service;

import com.example.Todoapp.data.entity.TodoInfo;
import com.example.Todoapp.dto.TodoInfoDTO;

import java.util.Optional;

public interface ITodoInfoService {
    void deleteByTodoId(long id);
    void deleteByTodoIdBetween(int start, int end);
    long countTodos();
    Iterable<TodoInfoDTO> mostXUrgentTodos(int todos);
    Iterable<TodoInfoDTO> findAllTodos();
    Iterable<TodoInfoDTO> findTodosByTitleLike(String title);
    Iterable<TodoInfoDTO> findTodosByMonthsBetween(int start, int end);
    Optional<TodoInfoDTO> findTodoById(long id);
    TodoInfoDTO saveTodo(TodoInfoDTO todoInfoDTO);

}
