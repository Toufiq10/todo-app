package com.elearn.todo_list.model.request;

import com.elearn.todo_list.model.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoRequestModel {

    @NotBlank(message = "Title Cannot be empty")
    private String title;
    @NotBlank(message = "Description helps in providing and overview")
    private String description;
    @NotNull(message = "Please set a priority to track better!")
    private Todo.Priority priority;
}
