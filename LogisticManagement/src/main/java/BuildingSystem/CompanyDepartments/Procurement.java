package BuildingSystem.CompanyDepartments;

import UserSystem.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Procurement")
public class Procurement {
	@Id
	@GeneratedValue
	long id;
	String location;
	String telephone;
	String address;
	
	
	@OneToOne
	@JoinColumn(name = "user_id",nullable = true)
	User yonetici;
	
	public Procurement() {
		
	}
	
	public Procurement(String Konum,String Telefon,String Adres, User user) {
		location = Konum;
		telephone = Telefon;
		address = Adres;
//		yonetici = user;
	}
}
