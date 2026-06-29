package PaymentSystem;

import UserSystem.User;
import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Bir user'ın birden fazla hesabı olabilir
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double balance;

    private String iban;

    public Account() {}

    public Account(User user, double balance, String iban) {
        this.user = user;
        this.balance = balance;
        this.iban = iban;
    }

    // getter setter
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    
    public void setAccountOfUser(User user) {
    	this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
    
}