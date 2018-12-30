package pl.umcs.lisowska;

import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.providers.AccountsProvider;
import pl.umcs.lisowska.providers.UserProvider;

import java.util.List;

import static pl.umcs.lisowska.common.enums.Gender.FEMALE;
import static pl.umcs.lisowska.common.enums.Gender.MALE;

public class UserManagerApp {
    private static UserProvider userProvider = new UserProvider();
    private static AccountsProvider accountsProvider;

    public static void main(String[] args) {
        long userUpdateId = 3;
        long userDeleteId = 2;

        userHandler(userUpdateId, userDeleteId);

        List<User> users = userProvider.fetchAllUsers();
        for (User usr: users) {
            accountHandler(usr);
        }
    }

    public static void userInit(){
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

    public static void userHandler(long userUpdateId, long userDeleteId){

        List<User> userList = userProvider.fetchAllUsers();
        if(userList == null || userList.isEmpty()){
            System.out.println("[CLIENT] Empty list! Commencing initiation...");
            userInit();
        }else {
            userProvider.updateUser(userUpdateId);
            userProvider.fetchAllUsers();
            userProvider.deleteUser(userDeleteId);
            userProvider.fetchAllUsers();
        }
    }

    public static void accountHandler(User user){
        accountsProvider = new AccountsProvider(user.getId());
        accountsProvider.addAccount();
        accountsProvider.addAccount();
        accountsProvider.fetchAllAccounts();
    }
}
