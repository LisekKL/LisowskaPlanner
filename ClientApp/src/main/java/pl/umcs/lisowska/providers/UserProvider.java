package pl.umcs.lisowska.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.umcs.lisowska.common.User;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static pl.umcs.lisowska.common.enums.Gender.MALE;

/**
 * Hello world!
 *
 */
public class UserProvider
{
    private static final Logger logger = LoggerFactory.getLogger(UserProvider.class);
    private static final String USERS_URL = "http://localhost:8080/users";
    private RestTemplate restTemplate = new RestTemplate();

    public void addUser(User user){
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange(USERS_URL, HttpMethod.POST, request, User.class);

        User responseBody = response.getBody();

        logger.info("[addAccount] Created user " + responseBody, responseBody);
    }

    public List<User> fetchAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                USERS_URL,
                GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        logger.info("Fetched all users {}", users);
        return users;
    }

    public void updateUser(long id) {
        User updatedInstance = new User(id, "Change", "Updated", 11, MALE);

        String entityUrl = USERS_URL + "/" + id;
        HttpEntity<User> requestUpdate = new HttpEntity<>(updatedInstance);
        restTemplate.exchange(entityUrl, HttpMethod.PUT, requestUpdate, Void.class);
    }

    public void deleteUser(long id) {
        String entityUrl = USERS_URL + "/" + id;
        restTemplate.delete(entityUrl);
    }
}
