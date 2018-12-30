package pl.umcs.lisowska.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String countryCode;
    private String accountNumber;
    private double balance;

    //    RELATIONS
    @ManyToOne
    @JoinColumn(name = "holder_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User accountHolder;

    public User getAccountHolder(){
        return accountHolder;
    }

    public Account(User user){
        countryCode = "PL";
        accountHolder = user;
        accountNumber = countryCode + "000011110000" + id;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountHolder(User accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
