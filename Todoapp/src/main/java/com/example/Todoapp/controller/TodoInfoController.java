package com.example.Todoapp.controller;

import com.example.Todoapp.dto.TodoInfoDTO;
import com.example.Todoapp.service.ITodoInfoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/todos")
public class TodoInfoController {
    ITodoInfoService iTodoInfoService;

    public TodoInfoController(ITodoInfoService iTodoInfoService) {
        this.iTodoInfoService = iTodoInfoService;
    }

    @GetMapping("/all")
    public Iterable<TodoInfoDTO> getAllTodos()
    {
        return iTodoInfoService.findAllTodos();
    }

    @PostMapping("/saveArray")
    public TodoInfoDTO[] saveArray (@RequestBody TodoInfoDTO[] todoInfoDTOS)
    {
        Arrays.stream(todoInfoDTOS).forEach(iTodoInfoService::saveTodo);

        return todoInfoDTOS;
    }

    @GetMapping("/titleLike") //query problem
    public Iterable<TodoInfoDTO> titleLike(@RequestParam(value = "tit") String title)
    {
        return iTodoInfoService.findTodosByTitleLike(title);
    }

    @PostMapping("/save")
    public TodoInfoDTO save (@RequestBody TodoInfoDTO todoInfoDTO)
    {
        return iTodoInfoService.saveTodo(todoInfoDTO);
    }

    @GetMapping("/id")
    public TodoInfoDTO findById (@RequestParam(value = "id", required = false, defaultValue = "1") String id)
    {
        return iTodoInfoService.findTodoById(Long.parseLong(id)).orElse(new TodoInfoDTO());
    }

    @GetMapping("/month/between")
    public Iterable<TodoInfoDTO> findByMonthBetween(@RequestParam(value = "start", required = false, defaultValue = "1") int start,
                                                 @RequestParam(value = "end", required = false, defaultValue = "12") int end)
    {
        return iTodoInfoService.findTodosByMonthsBetween(start, end);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") String id)
    {
        iTodoInfoService.deleteByTodoId(Long.parseLong(id));

        return id + ".row" + " deleted";
    }

    @GetMapping("/deletes")
    public String deleteTodos(@RequestParam(value = "start") int start, @RequestParam(value = "end") int end)
    {
        iTodoInfoService.deleteByTodoIdBetween(start, end);

        return "Todo's between " + start + " and " + end + " have been deleted";
    }

    @GetMapping("/count")
    public long count()
    {
        return iTodoInfoService.countTodos();
    }

    @GetMapping("/urgent")
    public Iterable<TodoInfoDTO> urgent(@RequestParam(value = "dl", required = false, defaultValue = "5") int limit)
    {
        return StreamSupport.stream(iTodoInfoService.mostXUrgentTodos(limit).spliterator(), false).
                filter(todoInfoDTO -> (todoInfoDTO.getExpectedEndDate().isAfter(LocalDate.now()))).limit(limit)
                .collect(Collectors.toList());
    }

}
