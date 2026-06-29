package NotifySystem;

import java.sql.Date;
import java.time.LocalDate;

import UserSystem.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue
    private long id;

    private String mesaj;

    private LocalDate tarih;

    @Enumerated(EnumType.STRING)
    private notificationStatus durum;

    @ManyToOne
    @JoinColumn(name = "alici_id",nullable = true)
    private User alici;
    
    private String gonderen_taraf;
    
    public void setNotificationStatus(notificationStatus new_state) {
    	durum = new_state;
    }
    
    public notificationStatus getNotifyStatus() {
    	return durum;
    }
    
    public long getId() {
    	return id;
    }

	public String getMesaj() {
		return mesaj;
	}

	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}
	
	public User getReceiver() {
		return alici;
	}
	
	public void setReceiever(User user) {
		alici = user;
	}

	public String getSender() {
		return gonderen_taraf;
	}

	public void setSender(String gonderen_taraf) {
		this.gonderen_taraf = gonderen_taraf;
	}
}