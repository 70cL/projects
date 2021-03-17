package com.example.Todoapp.dal;

import com.example.Todoapp.entity.TodoInfo;
import com.example.Todoapp.exceptions.RepositoryException;
import com.example.Todoapp.repository.ITodoInfoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoHelper {
    private final ITodoInfoRepository iTodoInfoRepository;

    public TodoHelper(ITodoInfoRepository iTodoInfoRepository)
    {
        this.iTodoInfoRepository = iTodoInfoRepository;
    }

    public Iterable<TodoInfo> findAllTodos()
    {
        try {
            return iTodoInfoRepository.findAll();
        }
        catch (Throwable ex)
        {
            throw new RepositoryException("findAllTodos", ex);
        }
    }

    public void deleteByTodoId(long id)
    {
        try {
            iTodoInfoRepository.deleteById(id);
        }
        catch (Throwable ex)
        {
            throw new RepositoryException("TodoHelper.deleteByTodoId", ex);
        }
    }

    public Iterable<TodoInfo> findByMonthBetweenTodo(int start, int end)
    {
        try {
            return iTodoInfoRepository.findByMonthBetween(start, end);
        }
        catch (Throwable ex)
        {
            throw new RepositoryException("TodoHelper.findByMonthBetweenTodo", ex);
        }
    }

    public Iterable<TodoInfo> urgentTodos(int urgentNumber)
    {
        try {
            return iTodoInfoRepository.closestTodoDeadline(urgentNumber);
        }
        catch (Throwable ex)
        {
            throw new RepositoryException("TodoHelper.urgentTodos", ex);
        }
    }

    public Optional<TodoInfo> findByTodoId(long id)
    {
        try {
            return iTodoInfoRepository.findById(id);
        }
        catch (Throwable ex)
        {
            throw new RepositoryException("TodoHelper.findByTodoId", ex);
        }
    }

    public long countTodos()
    {
        try {
            return iTodoInfoRepository.count();
        }
        catch (Throwable ex)
        {
            throw new RepositoryException("TodoHelper.countTodos", ex);
        }
    }

    public TodoInfo save(TodoInfo todoInfo)
    {
        try {
            return iTodoInfoRepository.save(todoInfo);
        }
        catch (Throwable ex)
        {
            throw new RepositoryException("TodoHelper.save", ex);
        }
    }
}
