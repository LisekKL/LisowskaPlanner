package pl.umcs.lisowska.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umcs.lisowska.model.ToDoItem;
import pl.umcs.lisowska.repository.ToDoItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private final ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public ToDoItem saveToDo(ToDoItem item) {

        return toDoItemRepository.save(item);
    }

    public List<ToDoItem> findAllToDoItems() {
        Iterable<ToDoItem> itemsIterable = toDoItemRepository.findAll();

        return (List<ToDoItem>) itemsIterable;
    }

    public ToDoItem findToDoById(Long id) {
        Optional<ToDoItem> item = toDoItemRepository.findById(id);
        return item.orElse(null);
    }

    public ToDoItem updateToDo(ToDoItem item) {

        return toDoItemRepository.save(item);
    }

    public void deleteToDo(Long id) {
        toDoItemRepository.deleteById(id);
    }

}

