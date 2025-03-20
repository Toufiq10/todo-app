package com.elearn.todo_list.controller;

import com.elearn.todo_list.exception.ValidationException;
import com.elearn.todo_list.model.Todo;
import com.elearn.todo_list.model.request.TodoRequestModel;
import com.elearn.todo_list.model.request.TodoStatusUpdateModel;
import com.elearn.todo_list.service.TodoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody TodoRequestModel todo) {
        var response = todoService.create(todo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(
            @RequestParam(value = "status",required = false) String status,
            @RequestParam(value = "start", required = false) String starDate,
            @RequestParam(value = "end",required = false) String endDate) {
        var todos = todoService.getTodos(status,starDate,endDate);
        return ResponseEntity.ok(todos);
    }

    @PatchMapping
    public ResponseEntity<Todo> updateStatus(@RequestParam("id") String id,
                                       @Valid @RequestBody TodoStatusUpdateModel model) throws ValidationException {
        var todo = todoService.update(id,model);
        return ResponseEntity.ok(todo);
    }

}
