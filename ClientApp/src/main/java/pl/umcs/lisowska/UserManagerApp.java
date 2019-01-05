package pl.umcs.lisowska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.common.enums.Gender;
import pl.umcs.lisowska.providers.AccountsProvider;
import pl.umcs.lisowska.providers.UserProvider;

import java.util.List;

import static pl.umcs.lisowska.common.enums.Gender.FEMALE;
import static pl.umcs.lisowska.common.enums.Gender.MALE;

@SpringBootApplication
@EnableDiscoveryClient
public class UserManagerApp {
    private static UserProvider userProvider = new UserProvider();
    private static AccountsProvider accountsProvider;
    private static final long userUpdateId = 1;
    private static final long userDeleteId = 3;

    public static void main(String[] args) {
        //userInit();
        //updateUser();
        //deleteUser();

        List<User> users = userProvider.fetchAllUsers();
        for (User usr: users) {
            accountHandler(usr);
        }

        SpringApplication.run(UserManagerApp.class, args);
    }

    private static void userInit(){
        User pawelMickiewicz = new User("Paweł", "Mickiewicz", 21, MALE);
        User janKowalski = new User("Jan", "Kowalski", 33, MALE);
        User ewaKowalska = new User("Ewa", "Kowalska", 29, FEMALE);
        User krzysztofKrawczyk = new User("Krzysztof", "Krawczyk", 65, MALE);
        User magdaPawlak = new User("Magda", "Pawlak", 23, FEMALE);
        User michalAdamczyk = new User("Michał", "Adamczyk", 43, MALE);

        userProvider.addUser(pawelMickiewicz);
        userProvider.addUser(janKowalski);
        userProvider.addUser(ewaKowalska);
        userProvider.addUser(krzysztofKrawczyk);
        userProvider.addUser(magdaPawlak);
        userProvider.addUser(michalAdamczyk);
    }
    private static void addUser(){
        User user = new User("POST", "APP", 18, Gender.MALE);
        userProvider.addUser(user);
    }

    private static void updateUser(){
        if(userProvider.getUserById(userUpdateId) == null){
            System.out.println("[APP] User for update with id " + userUpdateId + " was not found! Update aborted...");
        }else {
            userProvider.updateUser(userUpdateId);
        }
    }

    private static void deleteUser(){
        if(userProvider.getUserById(userDeleteId) == null) {
            System.out.println("[APP] User for update with id " + userUpdateId + " was not found! Update aborted...");
        }else{
            userProvider.deleteUser(userDeleteId);
        }
    }

    private static void accountHandler(User user){
        accountsProvider = new AccountsProvider(user);
        accountsProvider.addAccount();
        accountsProvider.addAccount();
        accountsProvider.fetchAllAccounts();
    }
}
