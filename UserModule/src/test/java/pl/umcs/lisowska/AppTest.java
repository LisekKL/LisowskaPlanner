package pl.umcs.lisowska;

import org.junit.Test;
import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.common.enums.Gender;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    @Test
    public void addingFemaleUser()
    {
        User user = new User("Kasia", "Kowalska", 17, Gender.FEMALE);
        System.out.println("[TEST] user last letter: " + user.getFirstName().substring(user.getFirstName().length() - 1));
        assertTrue(user.getFirstName().substring(user.getFirstName().length() - 1).equals("a"));
    }
}
