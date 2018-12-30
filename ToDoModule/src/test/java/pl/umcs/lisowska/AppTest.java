package pl.umcs.lisowska;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.umcs.lisowska.repository.ToDoItemRepository;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Autowired
    ToDoItemRepository toDoItemRepository;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
