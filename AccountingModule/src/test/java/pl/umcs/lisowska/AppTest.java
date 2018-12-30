package pl.umcs.lisowska;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.common.enums.Gender;
import pl.umcs.lisowska.common.Account;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void balanceInsufficientToMakeWithdrawal(){
        User kasiaKowalska = new User("Kasia", "Kowalska", 20, Gender.FEMALE);
        User janKowalski = new User("Jan", "Kowalski", 25, Gender.MALE);
        Account sender = new Account(janKowalski);

        Account recipient = new Account(kasiaKowalska);
        double amount = 150;
    }
}
