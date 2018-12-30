package pl.umcs.lisowska.repository;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.lisowska.model.ToDoItem;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {

}

