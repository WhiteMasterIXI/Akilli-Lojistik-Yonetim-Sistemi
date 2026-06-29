package dao;

import java.util.List;

import BuildingSystem.CompanyDepartments.WareHouse;
import Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class WareHouseDao {

    public static boolean save(WareHouse depo) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(depo);
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

    public static boolean update(WareHouse depo) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(depo);
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

    public static boolean delete(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            WareHouse role = em.find(WareHouse.class, id);
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

    public static WareHouse findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(WareHouse.class, id);
        } finally {
            em.close();
        }
    }

    public static List<WareHouse> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<WareHouse> query =
                    em.createQuery("FROM WareHouse", WareHouse.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static WareHouse findByUser(long user_id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT w FROM WareHouse w WHERE w.yonetici.id = :user_id",
                    WareHouse.class)
                    .setParameter("user_id", user_id)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

}
