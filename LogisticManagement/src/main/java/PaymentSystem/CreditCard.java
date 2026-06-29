package PaymentSystem;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import UserSystem.User;

@Entity
@Table(name = "credit_cards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    
    private double cardLimit;
    
    private double Debt;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CreditCard() {
    }
    
    public double GetDebt() {
    	return Debt;
    }

    public CreditCard(String cardNumber, User user) {
        this.cardNumber = cardNumber;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void setCreatedAt(LocalDateTime time) {
    	createdAt = time;
    }

	public double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}
}