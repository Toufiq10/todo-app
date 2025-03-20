package com.elearn.todo_list.repository.templates;

import com.elearn.todo_list.model.Todo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.elearn.todo_list.util.TodoHelper.*;

@Component
@AllArgsConstructor
@Slf4j
public class CustomMongoQuery {

    private final MongoTemplate mongoTemplate;

    public List<Todo> getTodosWithFilter(String startDate, String endDate, String status) {
        Query query = new Query();

        if(hasDateFilter(startDate,endDate)) {
            Pair<Date, Date> pair = toDate(startDate,endDate);
            Date start = pair.getLeft();
            Date end = pair.getRight();
            query.addCriteria(Criteria.where("createdOn").gte(start).lt(end));
        }

        if(hasStatusFilter(status)) {
            query.addCriteria(Criteria.where("status").is(status));
        }

        log.info(query.toString());
        return mongoTemplate.find(query, Todo.class);
    }
}
