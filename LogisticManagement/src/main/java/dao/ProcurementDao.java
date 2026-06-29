package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import BuildingSystem.CompanyDepartments.Procurement;
import Utils.JPAUtil;

public class ProcurementDao {

    public void save(Procurement p) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(p);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void update(Procurement p) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(p);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Procurement p = em.find(Procurement.class, id);
            if (p != null) {
                em.remove(p);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Procurement findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Procurement.class, id);
        } finally {
            em.close();
        }
    }

    public List<Procurement> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Procurement> query =
                    em.createQuery("FROM Procurement", Procurement.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Procurement> findByManager(long userId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Procurement p WHERE p.yonetici.id = :uid",
                    Procurement.class)
                    .setParameter("uid", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}