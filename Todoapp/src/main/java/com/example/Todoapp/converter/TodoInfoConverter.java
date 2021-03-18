package com.example.Todoapp.converter;

import com.example.Todoapp.data.entity.TodoInfo;
import com.example.Todoapp.dto.TodoInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class TodoInfoConverter {
    public TodoInfoDTO TodoInfoToTodoInfoDTO(TodoInfo todoInfo)
    {
        TodoInfoDTO todoInfoDTO = new TodoInfoDTO();

        todoInfoDTO.setId(todoInfo.getId());
        todoInfoDTO.setUsername(todoInfo.getUsername());
        todoInfoDTO.setTitle(todoInfo.getTitle());
        todoInfoDTO.setDescription(todoInfo.getDescription());
        todoInfoDTO.setStartDate(todoInfo.getStartDate());
        todoInfoDTO.setExpectedEndDate(todoInfo.getExpectedEndDate());
        todoInfoDTO.setEndDate(todoInfo.getEndDate());
        todoInfoDTO.setCompleted(todoInfo.isCompleted());

        return todoInfoDTO;
    }

    public TodoInfo TodoInfoDTOToTodoInfo(TodoInfoDTO todoInfoDTO)
    {
        TodoInfo todoInfo = new TodoInfo();

        todoInfo.setId(todoInfoDTO.getId());
        todoInfo.setUsername(todoInfoDTO.getUsername());
        todoInfo.setTitle(todoInfoDTO.getTitle());
        todoInfo.setDescription(todoInfoDTO.getDescription());
        todoInfo.setStartDate(todoInfoDTO.getStartDate());
        todoInfo.setExpectedEndDate(todoInfoDTO.getExpectedEndDate());
        todoInfo.setEndDate(todoInfoDTO.getEndDate());
        todoInfo.setCompleted(todoInfoDTO.isCompleted());

        return todoInfo;
    }

}
