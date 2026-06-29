package Controller;

import java.util.List;
import java.util.Set;

import RoleSystem.Authority;
import RoleSystem.Role;

import dao.RoleDao;

public class RoleController {
	
	public static boolean createRole(Role role) {
		if(!RoleDao.save(role)) return false;
		
		return true;
	}
	
	public static List<Role> getRoles(){
		return RoleDao.findAll();
	}
	
	public static void UpdateBasicRoles() {
		
        String[] roleNames = {
                "Admin",
                "Musteri",
                "Sirket_Sahibi",
                "Depo_Yoneticisi",
                "Urun_Yoneticisi",
                "Kargo_Yetkilisi"
        };
        
        List<Role> roles = RoleController.getRoles();
        

        for (Role role : roles) {

        	String role_name = role.getName();

            // ROLE AUTHORITY MAPPING (basit başlangıç)
            if (role_name.equals("Admin")) {
                role.setAuthorities(Set.of(
                        Authority.BILGILERIMI_GORUNTULE
                ));
            }

            else if (role_name.equals("Musteri")) {
                role.setAuthorities(Set.of(
                		Authority.MUSTERISIPARIS_GORUNTULE,
                        Authority.URUNLERI_GORUNTULE,
                        Authority.SEPET_GORUNTULE,
                        Authority.BILGILERIMI_GORUNTULE,
                        Authority.URUN_SATINAL
                ));
            }

            else if (role_name.equals("Urun_Yoneticisi")) {
                role.setAuthorities(Set.of(
                        Authority.URUN_YONET,
                        Authority.URUNLERI_GORUNTULE
                ));
            }

            else if (role_name.equals("Depo_Yoneticisi")) {
                role.setAuthorities(Set.of(
                        Authority.DEPO_YONET,
                        Authority.URUN_KABULETME
                ));
            }

            else if (role_name.equals("Kargo_Yetkilisi")) {
                role.setAuthorities(Set.of(
                        Authority.KURYESIPARIS_GORUNTULE
                ));
            }

            else if(role_name.equals("Sirket_Sahibi")) {
                role.setAuthorities(Set.of(
                        Authority.BILGILERIMI_GORUNTULE,
                        Authority.DEPOLARI_GORUNTULE
                ));
            }

            RoleDao.update(role);
        }
        }
	
	
	public static void CreateBasicRoles() {
		
		// eklenmişse alt taraf bir daha çalışmasın.
		if(RoleDao.findByName("Admin") != null)return;

        String[] roleNames = {
                "Admin",
                "Musteri",
                "Sirket_Sahibi",
                "Depo_Yoneticisi",
                "Urun_Yoneticisi",
                "Kargo_Yetkilisi"
        };
        

        for (String name : roleNames) {

            Role role = new Role();
            role.SetName(name);

            // ROLE AUTHORITY MAPPING (basit başlangıç)
            if (name.equals("Admin")) {
                role.setAuthorities(Set.of(
                		Authority.BILGILERIMI_GORUNTULE
                ));
            }

            else if (name.equals("Musteri")) {
                role.setAuthorities(Set.of(
                		Authority.MUSTERISIPARIS_GORUNTULE,
                        Authority.URUNLERI_GORUNTULE,
                        Authority.SEPET_GORUNTULE,
                        Authority.BILGILERIMI_GORUNTULE,
                        Authority.URUN_SATINAL
                ));
            }

            else if (name.equals("Urun_Yoneticisi")) {
                role.setAuthorities(Set.of(
                        Authority.URUN_YONET,
                        Authority.URUNLERI_GORUNTULE
                ));
            }

            else if (name.equals("Depo_Yoneticisi")) {
                role.setAuthorities(Set.of(
                        Authority.DEPO_YONET,
                        Authority.URUN_KABULETME
                ));
            }

            else if (name.equals("Kargo_Yetkilisi")) {
                role.setAuthorities(Set.of(
                        Authority.KURYESIPARIS_GORUNTULE
                ));
            }

            else if(name.equals("Sirket_Sahibi")) {
                role.setAuthorities(Set.of(
                        Authority.BILGILERIMI_GORUNTULE,
                        Authority.DEPOLARI_GORUNTULE
                ));
            }

            RoleDao.save(role);
        }
        }
	
	public static Role findRoleByName(String role_name) {
		return RoleDao.findByName(role_name);
	}
}
