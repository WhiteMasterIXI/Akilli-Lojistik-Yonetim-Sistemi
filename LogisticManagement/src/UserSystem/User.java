package Model;
import jakarta.persistence.*;
import java.util.List;
import NotifySystem.Notification;
import RoleSystem.Authority;

//@Entity
//@Table(name = "users")
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isim;
    private String soyisim;
    private String adres;
    private String telefon;

//    @Column(unique = true)
    private String email;

    private String hashPass;

//    @ManyToOne
//    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user_id")
    private List<Notification> bildirimler;
	
	public User(int id,String isim,String soyisim,String adres,String telefon,String email,String hashPass,List<Authority> yetkiler,int rol) {
		Id = id;
		Isim = isim;
		Soyisim = soyisim;
		Adres = adres;
		Telefon = telefon;
		Email = email;
		HashPass = hashPass;
		Yetkiler = yetkiler;
		Rol = rol;
	}
	
	public void YetkileriAta(List<Authority> yetkiler) {
		Yetkiler = yetkiler;
	}
	public void BildirimiSil() {}
	public void BildirimleriTemizle() {}
	public void SifreDegistir() {}
	public void AdresDegistir() {}
	public void IsimSoyisimDegistir() {}
	public void BilgileriDegitir() {}
}
