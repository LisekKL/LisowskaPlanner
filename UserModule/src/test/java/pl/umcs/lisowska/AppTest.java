package pl.umcs.lisowska;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.common.enums.Gender;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
//    private static final String USERS_URL = "http://localhost:8080/users";
//
//    private RestTemplate restTemplate = new RestTemplate();
//
//    @Test
//    public void addUserTest(){
//        // GIVEN
//        User user = new User("Adam", "Małysz", 34, Gender.MALE);
//        HttpEntity<User> request = new HttpEntity<>(user);
//        ResponseEntity<User> response = restTemplate.exchange(USERS_URL, HttpMethod.POST, request, User.class);
//
//        assertTrue(response.getBody() != null && response.getBody().getFirstName() == "Adam");
//    }

    @Test
    public void addingFemaleUser()
    {
        User user = new User("Kasia", "Kowalska", 17, Gender.FEMALE);
        System.out.println("[TEST] user last letter: " + user.getFirstName().substring(user.getFirstName().length() - 1));
        assertTrue(user.getFirstName().substring(user.getFirstName().length() - 1).equals("a"));
    }
}
