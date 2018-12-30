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
        ToDoItem savedItem = toDoItemRepository.save(item);

        return savedItem;
    }

    public List<ToDoItem> findAllToDoItems() {
        Iterable<ToDoItem> itemsIterable = toDoItemRepository.findAll();
        List<ToDoItem> items = (List<ToDoItem>) itemsIterable;

        return items;
    }

    public ToDoItem findToDoById(Long id) {
        Optional<ToDoItem> item = toDoItemRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        }
        return null;
    }

    public ToDoItem updateToDo(ToDoItem item) {
        ToDoItem updatedItem = toDoItemRepository.save(item);

        return updatedItem;
    }

    public void deleteToDo(Long id) {
        toDoItemRepository.deleteById(id);
    }

}

