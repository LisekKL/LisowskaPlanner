package pl.umcs.lisowska.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.umcs.lisowska.common.Account;
import pl.umcs.lisowska.common.User;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

public class AccountsProvider {
    private static final Logger logger = LoggerFactory.getLogger(UserProvider.class);
    private static long USER_ID = 0;
    private static String USERS_URL;
    private static String ACCOUNTS_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private User user;

    public AccountsProvider(User user){
        this.user = user;
        USER_ID = user.getId();
        USERS_URL = "http://localhost:8080/users/" + USER_ID;
        ACCOUNTS_URL = "http://localhost:8081/accounts/";
    }

    public void addAccount(){
        User user = new User();
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange(USERS_URL, HttpMethod.GET, request, User.class);

        Account account = new Account(user);
        HttpEntity<Account> requestAccount = new HttpEntity<>(account);
        ResponseEntity<Account> responseAccount = restTemplate.exchange(ACCOUNTS_URL, HttpMethod.POST, requestAccount, Account.class);

        logger.info("[addAccount] Created account " + account, responseAccount.getBody());
    }

    public void fetchAllAccounts() {
        ResponseEntity<List<Account>> response = restTemplate.exchange(
                ACCOUNTS_URL + "/" + USER_ID,
                GET,
                null,
                new ParameterizedTypeReference<List<Account>>() {
                });
        List<Account> accounts = response.getBody();
        logger.info("Fetched all accounts for user with id " + USER_ID + "{}", accounts);
    }

    public void updateAccount(long accountId) {
        User user = new User();
        HttpEntity<User> requestUser = new HttpEntity<>(user);
        ResponseEntity<User> responseUser = restTemplate.exchange(USERS_URL, HttpMethod.GET, requestUser, User.class);
        user = requestUser.getBody();

        Account updatedInstance = new Account(user);
        HttpEntity<Account> requestUpdate = new HttpEntity<>(updatedInstance);
        ResponseEntity<Account> responseAccount = restTemplate.exchange(ACCOUNTS_URL, HttpMethod.PUT, requestUpdate, Account.class);

        logger.info("Updated account for user with id " + USER_ID + "{}", responseAccount.getBody());
    }

    public void deleteAccount(long accountId) {
        String entityUrl = ACCOUNTS_URL + "/" + accountId;
        restTemplate.delete(entityUrl);
    }
}
