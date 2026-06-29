package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import ShoppingSystem.Products.AllowedComponent;
import Utils.JPAUtil;

public class AllowedComponentDao {

    public static boolean save(AllowedComponent ac) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(ac);
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

            AllowedComponent ac = em.find(AllowedComponent.class, id);
            if (ac != null) {
                em.remove(ac);
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

    public static AllowedComponent findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(AllowedComponent.class, id);
        } finally {
            em.close();
        }
    }

    public static List<AllowedComponent> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<AllowedComponent> query =
                    em.createQuery("FROM AllowedComponent", AllowedComponent.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<AllowedComponent> findByParentProduct(long productId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT a FROM AllowedComponent a WHERE a.parent.id = :pid",
                    AllowedComponent.class)
                    .setParameter("pid", productId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}