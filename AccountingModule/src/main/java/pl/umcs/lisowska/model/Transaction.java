package pl.umcs.lisowska.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.umcs.lisowska.common.Account;
import pl.umcs.lisowska.model.enums.TransactionStatus;
import pl.umcs.lisowska.model.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_id")
    @JsonIgnore
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_account_id")
    @JsonIgnore
    private Account recipient;

    @NotNull
    private Date transactionDate;

    @NotNull
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String description;

    public Transaction(){
        this.transactionDate = new Date();
        this.status = TransactionStatus.STARTED;
    }
    public Transaction(Account sender, Account recipient, double amount){
        this.transactionDate = new Date();
        this.status = TransactionStatus.STARTED;
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Long getId(){return this.id;}
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", transactionDate='" + transactionDate + '\'' +
//                ", amount='" + amount + '\'' +
//                ", status=" + status +
//                ", type=" + type +
//                ", description=" + description +
//                ", sender_account_id=" + sender.getId() +
//                ", recipient_account_id=" + recipient.getId() +
//                '}';
//    }

}
