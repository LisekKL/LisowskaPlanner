package pl.umcs.lisowska.providers;

import com.netflix.discovery.EurekaClient;
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

public class UserProvider
{
    private static final Logger logger = LoggerFactory.getLogger(UserProvider.class);
    private static String USERS_URL = "";
    private RestTemplate restTemplate = new RestTemplate();
    private EurekaClient discoveryClient;

    public UserProvider(){
        USERS_URL = "http://localhost:8080/users";
        //USERS_URL = discoveryClient.getNextServerFromEureka("users", false).getHomePageUrl();
    }

    public UserProvider(EurekaClient discoveryClient){
        this.discoveryClient = discoveryClient;
        USERS_URL = discoveryClient.getNextServerFromEureka("users", false).getHomePageUrl();
    }

    public void addUser(User user){
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange(USERS_URL, HttpMethod.POST, request, User.class);

        User responseBody = response.getBody();

        logger.info("[addAccount] Created user " + responseBody, responseBody);
    }

    public User getUserById(long userId){
        User user = new User();
        HttpEntity<User> requestUser = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange(USERS_URL + "/" + userId, HttpMethod.GET, requestUser, User.class);
        return response.getBody();
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
        User user = new User();
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> responseUser = restTemplate.exchange(USERS_URL + "/" + id, HttpMethod.GET, request, User.class);

        User updatedInstance = responseUser.getBody();
        if (updatedInstance != null) {
            updatedInstance.setFirstName("PUT");
            updatedInstance.setLastName("APP");
            updatedInstance.setAge(10);
            request = new HttpEntity<>(updatedInstance);
            restTemplate.exchange(USERS_URL, HttpMethod.PUT, request, User.class);
        }
    }

    public void deleteUser(long id) {
        String entityUrl = USERS_URL + "/" + id;
        restTemplate.delete(entityUrl);
    }
}
