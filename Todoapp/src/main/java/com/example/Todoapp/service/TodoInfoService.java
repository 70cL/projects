package com.example.Todoapp.service;

import com.example.Todoapp.converter.TodoInfoConverter;
import com.example.Todoapp.data.dal.TodoHelper;
import com.example.Todoapp.dto.TodoInfoDTO;
import com.example.Todoapp.exceptions.DataServiceException;
import com.example.Todoapp.exceptions.RepositoryException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TodoInfoService implements ITodoInfoService{
    private final TodoHelper todoHelper;
    private final TodoInfoConverter todoInfoConverter;

    public TodoInfoService(TodoHelper todoHelper, TodoInfoConverter todoInfoConverter) {
        this.todoHelper = todoHelper;
        this.todoInfoConverter = todoInfoConverter;
    }

    @Override
    public void deleteByTodoId(long id) {
        try {
            todoHelper.deleteByTodoId(id);
        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("TodoInfoService.deleteByTodoId", ex.getCause());
        }
    }

    @Override
    public void deleteByTodoIdBetween(int start, int end) {
        try {
            todoHelper.deleteByTodoIdBetween(start, end);
        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("TodoInfoService.deleteByTodoIdBetween", ex.getCause());
        }
    }

    @Override
    public long countTodos() {
        try{
            return todoHelper.countTodos();
        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("TodoInfoService.countTodos", ex.getCause());
        }
    }

    @Override
    public Iterable<TodoInfoDTO> mostXUrgentTodos(int todos) {
        try {
            return StreamSupport.stream(todoHelper.urgentTodos(todos).spliterator(), false).
                    map(todoInfoConverter::TodoInfoToTodoInfoDTO).collect(Collectors.toList());
        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("TodoInfoService.mostXUrgentTodos", ex.getCause());
        }
    }

    @Override
    public Iterable<TodoInfoDTO> findAllTodos() {
        try {
            return StreamSupport.stream(todoHelper.findAllTodos().spliterator(), false).
                    map(todoInfoConverter::TodoInfoToTodoInfoDTO).collect(Collectors.toList());
        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("TodoInfoService.finAllTodos", ex.getCause());
        }
    }

    @Override
    public Iterable<TodoInfoDTO> findTodosByTitleLike(String title) {
        try {
            return StreamSupport.stream(todoHelper.findTodoByTitleLike(title).spliterator(),false).
                    map(todoInfoConverter::TodoInfoToTodoInfoDTO).collect(Collectors.toList());
        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("findTodosByTitleLike", ex.getCause());
        }
    }

    @Override
    public Iterable<TodoInfoDTO> findTodosByMonthsBetween(int start, int end) {
        try {
            return StreamSupport.stream(todoHelper.findByMonthBetweenTodo(start, end).spliterator(), false).
                    map(todoInfoConverter::TodoInfoToTodoInfoDTO).collect(Collectors.toList());
        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("findTodosByMonthsBetween", ex.getCause());
        }
    }

    @Override
    public Optional<TodoInfoDTO> findTodoById(long id) {
        try {
            return todoHelper.findByTodoId(id).stream().map(todoInfoConverter::TodoInfoToTodoInfoDTO).
                    collect(Collectors.toList()).stream().findFirst();
        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("TodoInfoService.findTodoById", ex.getCause());
        }
    }

    @Override
    public TodoInfoDTO saveTodo(TodoInfoDTO todoInfoDTO) {
        try {

            var todoInfo = todoInfoConverter.TodoInfoDTOToTodoInfo(todoInfoDTO);
            todoHelper.save(todoInfo);

            return todoInfoConverter.TodoInfoToTodoInfoDTO(todoInfo);

        }
        catch (RepositoryException ex)
        {
            throw new DataServiceException("TodoInfoService.saveTodo", ex.getCause());
        }
    }
}
