package UserSystem;
import jakarta.persistence.*;
import RoleSystem.Role;
import Services.ObserverPattern.Observer;
import Utils.HashUtil;
import dao.NotificationDao;

import java.util.List;
import NotifySystem.Notification;
import PaymentSystem.Account;
import PaymentSystem.CreditCard;
import RoleSystem.Authority;

@Entity
@Table(name = "users")
public class User implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Override
    public void update(Notification notification) {
        System.out.println("Bildirim: " + notification.getMesaj());
        notification.setReceiever(this);
    }
    
    @Override
    public User getUser() {
    	return this;
    }
    

    private String name;
    private String surname;
    private String telephone;

    @Column(unique = true)
    private String email;

    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditCard> CreditCards;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> Accounts;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "alici")
    private List<Notification> bildirimler;
	
	public User() {}
	
	public User(String name,String surname,String telephone,String email,String pass,Role role) {
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.email = email;
		this.password = pass;
		this.role = role;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String toString() {
		return name;
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role rol) {
		role = rol;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTelephone(String phone) {
		telephone = phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	
	
	 public String GetPass() {
		    return password;
		}
	 public void setPassword(String new_pass) {
		    password = new_pass;
	 }



		public void BildirimiSil(Notification n) {
		    if (bildirimler != null && n != null) {
		        bildirimler.remove(n);
		    }
		}

		public void BildirimleriTemizle() {
		    if (bildirimler != null) {
		        bildirimler.clear();
		    }
		}
		
	    public Account AddAccount(String iban, double balance) {

	        Account account = new Account();
	        account.setIban(iban);
	        account.setBalance(balance);
	        account.setAccountOfUser(this);

	        if (this.Accounts == null) {
	            this.Accounts = new java.util.ArrayList<>();
	        }

	        this.Accounts.add(account);
	        
	        return account;
	    }
	    
	    public CreditCard AddCreditCard(String cardNumber) {

	        CreditCard card = new CreditCard();
	        card.setCardNumber(cardNumber);
	        card.setUser(this);
	        card.setCreatedAt(java.time.LocalDateTime.now());

	        if (this.CreditCards == null) {
	            this.CreditCards = new java.util.ArrayList<>();
	        }

	        this.CreditCards.add(card);

	        return card;
	    }

}
