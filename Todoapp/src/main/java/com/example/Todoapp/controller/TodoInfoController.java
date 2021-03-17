package com.example.Todoapp.controller;

import com.example.Todoapp.entity.TodoInfo;
import com.example.Todoapp.repository.TodoInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
public class TodoInfoController {
    TodoInfoRepository todoInfoRepository;

    public TodoInfoController(TodoInfoRepository todoInfoRepository) {
        this.todoInfoRepository = todoInfoRepository;
    }

    @GetMapping("/all")
    public Iterable<TodoInfo> getAllTodos()
    {
        return todoInfoRepository.findAll();
    }

    @PostMapping("/savearray")
    public TodoInfo[] savearray (@RequestBody TodoInfo[] todoInfo)
    {
        Arrays.stream(todoInfo).forEach(todoInfoRepository::save);

        return todoInfo;
    }

    @PostMapping("/save")
    public TodoInfo save (@RequestBody TodoInfo todoInfo)
    {
        return todoInfoRepository.save(todoInfo);
    }

    @GetMapping("/id")
    public TodoInfo findById (@RequestParam(value = "id", required = false, defaultValue = "1") String id)
    {
        return todoInfoRepository.findById(Long.parseLong(id)).orElse(new TodoInfo());
    }

    @GetMapping("/month/between")
    public Iterable<TodoInfo> findByMonthBetween(@RequestParam(value = "start", required = false, defaultValue = "1") int start,
                                                 @RequestParam(value = "end", required = false, defaultValue = "12") int end)
    {
        return todoInfoRepository.findByMonthBetween(start, end);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") String id)
    {
        todoInfoRepository.deleteById(Long.parseLong(id));

        return id + ".row" + " deleted";
    }

    @GetMapping("/count")
    public long count()
    {
        return todoInfoRepository.count();
    }

    @GetMapping("/deadline")
    public Iterable<TodoInfo> deadLine(@RequestParam(value = "dl", required = false, defaultValue = "5") int limit)
    {
        return todoInfoRepository.closestTodoDeadline(limit);
    }

}
