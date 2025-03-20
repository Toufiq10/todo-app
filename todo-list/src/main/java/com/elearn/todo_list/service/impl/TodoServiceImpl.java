package com.elearn.todo_list.service.impl;

import com.elearn.todo_list.exception.ValidationException;
import com.elearn.todo_list.model.Todo;
import com.elearn.todo_list.model.request.TodoRequestModel;
import com.elearn.todo_list.model.request.TodoStatusUpdateModel;
import com.elearn.todo_list.repository.TodoRepository;
import com.elearn.todo_list.repository.templates.CustomMongoQuery;
import com.elearn.todo_list.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.elearn.todo_list.util.TodoHelper.*;

@Service
@AllArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final CustomMongoQuery mongoTemplate;

    @Override
    public Todo create(TodoRequestModel todo) {
        return todoRepository.save(buildTodo(todo));
    }

    @Override
    public List<Todo> getTodos(String status, String startDate, String endDate) {
        if(!hasDateFilter(startDate,endDate) && !hasStatusFilter(status)){
            return todoRepository.findAll();
        }
        return mongoTemplate.getTodosWithFilter(startDate,endDate,status);
    }

    @Override
    public Todo update(String id, TodoStatusUpdateModel model) throws ValidationException {
        var todo = todoRepository.findById(id).orElseThrow(
                ()-> {
                    log.error("Can't find Todo for id : {}",id);
                    return new ValidationException("Can't find Todo : "+ id
                            , HttpStatus.BAD_REQUEST,
                            ValidationException.ErrorType.CLIENT_ERROR);
                }
        );
        todo.setStatus(model.getStatus());
        todo.setNotes(model.getNotes());
        todo.setLastUpdatedOn(new Date());
       if(model.getStatus().name()
               .equals(Todo.Status.COMPLETED.name()))
           todo.setCompletedOn(new Date());

        return todoRepository.save(todo);
    }
}
