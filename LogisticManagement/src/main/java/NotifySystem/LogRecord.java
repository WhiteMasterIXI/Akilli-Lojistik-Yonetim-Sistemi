package NotifySystem;

import java.time.LocalDateTime;

import UserSystem.User;
import jakarta.persistence.*;

@Entity
@Table(name = "LogRecord")
public class LogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime tarih;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User actor;

    public LogRecord() {

    }
    
    public void setActor(User user) {
    	actor = user;
    }
    
    public User getActor() {
    	return actor;
    }

    public LogRecord(String message) {

        this.message = message;
        this.tarih = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTarih() {
        return tarih;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }
}