package com.elearn.todo_list.util;

import com.elearn.todo_list.model.Todo;
import com.elearn.todo_list.model.request.TodoRequestModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
public class TodoHelper {

    public static Pair<Date,Date> toDate(String startDate, String endDate) {
        Date start = null;
        Date end = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
             start = format.parse(startDate);
             end = format.parse(endDate);
        } catch (ParseException e) {
            log.error("Cannot Parse date : {} : {}",startDate, endDate);
        }
        return Pair.of(start,end);
    }



    public static boolean hasStatusFilter(String status){
        return !StringUtils.isBlank(status);
    }

    public static boolean hasDateFilter(String start, String end) {
            return !(StringUtils.isBlank(start) && StringUtils.isBlank(end));
    }

    public static Todo buildTodo(TodoRequestModel model) {
        return Todo.builder()
                .title(model.getTitle())
                .description(model.getDescription())
                .priority(model.getPriority())
                .createdOn(new Date())
                .status(Todo.Status.YET_TO_START)
                .build();
    }
}
