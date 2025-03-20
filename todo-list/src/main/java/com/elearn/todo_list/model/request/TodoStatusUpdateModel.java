package com.elearn.todo_list.model.request;

import com.elearn.todo_list.model.Todo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoStatusUpdateModel {
//    @NotBlank(message = "Cannot update Status with Empty Values")
    private Todo.Status status;

    @NotBlank(message = "Adding notes help you to find more insights in future, " +
            "Please provide some notes.")
    private String notes;
}
