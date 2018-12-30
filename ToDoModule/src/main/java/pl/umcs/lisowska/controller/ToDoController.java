package pl.umcs.lisowska.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.lisowska.model.ToDoItem;
import pl.umcs.lisowska.services.ToDoService;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    private static final Logger log = LoggerFactory.getLogger(ToDoController.class);

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ToDoItem> getItems() {
        List<ToDoItem> toDoItems = toDoService.findAllToDoItems();

        log.info("Retrieved TODO's {}", toDoItems);

        return toDoItems;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ToDoItem saveToDoItem(@RequestBody ToDoItem toDo) {
        ToDoItem savedItem = toDoService.saveToDo(toDo);

        log.info("Added TODO {}", savedItem);

        return savedItem;
    }

    @GetMapping("/{id}")
    public ToDoItem findToDoById(@PathVariable Long id) {
        return toDoService.findToDoById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id) {
        toDoService.deleteToDo(id);

        log.info("Deleted TODO with id {}", id);

        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ToDoItem updateToDo(@RequestBody ToDoItem user) {
        ToDoItem updatedToDo = toDoService.updateToDo(user);

        log.info("Updated User {}", updatedToDo);

        return updatedToDo;
    }
}
