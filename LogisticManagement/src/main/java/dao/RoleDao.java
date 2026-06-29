package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import RoleSystem.Role;
import Utils.JPAUtil;

public class RoleDao {

    public static boolean save(Role role) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(role);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public static boolean update(Role role) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(role);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public static boolean delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Role role = em.find(Role.class, id);
            if (role != null) {
                em.remove(role);
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public static Role findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Role.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Role> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Role> query =
                    em.createQuery("FROM Role", Role.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static Role findByName(String name) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT r FROM Role r WHERE r.name = :name",
                    Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
