package pl.umcs.lisowska.model;

import pl.umcs.lisowska.model.enums.ToDoPriority;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private ToDoPriority priority;

    public ToDoItem(){

    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ToDoPriority getPriority() {
        return priority;
    }

    public void setPriority(ToDoPriority priority) {
        this.priority = priority;
    }

//    @Override
//    public String toString() {
//        return "ToDoItem{" +
//                "id=" + id +
//                ", description='" + description + '\'' +
//                ", priority='" + priority + '\'' +
//                ", dueDate=" + dueDate +
//                '}';
//    }
}
