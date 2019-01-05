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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Account(){}
    public Account(User user){
        countryCode = "PL";
        this.user = user;
        accountNumber = countryCode + "000011110000_" + user.getId();
    }
//    public Account(long userId){
//        countryCode = "PL";
//        //this.userId = userId;
//        accountNumber = countryCode + "000011110000_" + userId;
//    }

    public User getUser(){return user;}
    public void setUser(User user){this.user = user;}

    public String getCountryCode(){return countryCode;}
    public void setCountryCode(String countryCode){this.countryCode = countryCode;}

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

    public boolean makeWithdrawal(double amount){
        if(amount > this.balance){ return false; }
        this.balance -= amount;
        return true;
    }

    public boolean makeDeposit(double amount){
        this.balance += amount;
        return true;
    }

    public Long getId(){ return this.id; }

    @Override
    public String toString() {
        String str = "Account{";
        if(id != null) {
            str += "id=" + id + "\n, ";
        }
        str += "user_id='" + user.getId() + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance + '\'' +
                '}';
        return str;
    }
}
