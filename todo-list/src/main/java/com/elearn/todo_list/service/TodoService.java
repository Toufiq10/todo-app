package com.elearn.todo_list.service;

import com.elearn.todo_list.exception.ValidationException;
import com.elearn.todo_list.model.Todo;
import com.elearn.todo_list.model.request.TodoRequestModel;
import com.elearn.todo_list.model.request.TodoStatusUpdateModel;

import java.util.List;

public interface TodoService {

    Todo create(TodoRequestModel todo);
    List<Todo> getTodos(String status, String startDate, String endDate);
    Todo update(String id, TodoStatusUpdateModel model) throws ValidationException;
}
