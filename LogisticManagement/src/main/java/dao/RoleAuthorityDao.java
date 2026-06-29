package dao;

import java.util.List;
import java.util.Set;

import RoleSystem.Role;

import RoleSystem.Authority;
import Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class RoleAuthorityDao {
	public static void addAuthorities(int rolId, List<Authority> yetkiler) {

	    EntityManager em = JPAUtil.getEntityManager();
	    EntityTransaction tx = em.getTransaction();

	    try {
	        tx.begin();

	        Role role = em.find(Role.class, rolId);

	        role.getAuthorities().addAll(yetkiler);

	        em.merge(role);

	        tx.commit();

	    } catch (Exception e) {
	        tx.rollback();
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}
	
	public static void removeRoleAuthorities(long rol_id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Role role = em.find(Role.class, rol_id);
            if (role != null) {
                role.getAuthorities().clear();  
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
	}
	
	public static void removeRoleAuthority(long rol_id,Authority authority) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Role role = em.find(Role.class, rol_id);
            
            if(role!=null)
                role.getAuthorities().remove(authority);  

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
	}
	
	public static Set<Authority> getAuthorities(long roleId) {
 
	    EntityManager em = JPAUtil.getEntityManager();

	    try {
	        Role role = em.find(Role.class, roleId);
	        return role.getAuthorities();
	    } finally {
	        em.close();
	    }
	}
}
