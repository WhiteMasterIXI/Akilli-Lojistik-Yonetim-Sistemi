package RoleSystem;
import java.util.Set;

import RoleSystem.Authority;
import jakarta.persistence.*;
@Entity
@Table(
		name = "Role",
		 uniqueConstraints = @UniqueConstraint(columnNames = "name")
		)

public class Role {
	
	@Id
	@GeneratedValue
	private Long id;
    private String name;
    private String introduce;
    
    // 
    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
        name = "role_authority",
        joinColumns = @JoinColumn(name = "role_id")
    )
    @Column(name = "authority")
    private Set<Authority> authorities;
    
    public long getId() {
    	return id;
    }
    
    
    public Role() {
    	
    }
    // O(1) olması için Set	authorities.contains(Authority.BILGILERIMI_GORUNTULE);
    
    public void SetName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
    public Set<Authority> getAuthorities(){
    	return authorities;
    }
    
    public void setAuthorities(Set<Authority> new_authorities) {
    	authorities = new_authorities;
    }

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
    
}