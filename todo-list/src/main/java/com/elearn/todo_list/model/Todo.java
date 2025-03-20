package com.elearn.todo_list.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection="todos")
public class Todo {

    @Id
    private String id;
    private String title;
    private String description;
    private Date createdOn;
    private Priority priority;
    private Status status;
    private Date completedOn;
    private Date lastUpdatedOn;
    private String notes;




    public enum Priority {
        HIGH,
        LOW,
        MEDIUM,
        URGENT
    }

    enum TaskType {
        ONE_TIME,
        DAILY,
        WEEKLY,
        MONTHLY
    }

    public enum Status {
        IN_PROGRESS,
        COMPLETED,
        MISSED,
        NOT_REQUIRED_ANYMORE,
        YET_TO_START

    }

}
