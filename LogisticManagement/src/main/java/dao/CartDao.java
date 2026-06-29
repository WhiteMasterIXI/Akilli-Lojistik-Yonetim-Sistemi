package dao;

import java.util.List;

import BuildingSystem.CompanyDepartments.Procurement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import ShoppingSystem.Cart;
import Utils.JPAUtil;

public class CartDao {

    public static boolean save(Cart cart) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(cart);
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

    public static boolean update(Cart cart) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(cart);
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

    public static boolean delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Cart cart = em.find(Cart.class, id);
            if (cart != null) {
                em.remove(cart);
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

    public static Cart findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Cart.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Cart> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Cart> query =
                    em.createQuery("FROM Cart", Cart.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static Cart findByUserId(long userId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT c FROM Cart c WHERE c.user.id = :uid",
                    Cart.class)
                    .setParameter("uid", userId)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;

        } finally {
            em.close();
        }
    }
    
}