package dao;

import java.util.List;

import BuildingSystem.Supplier;
import BuildingSystem.CompanyDepartments.WareHouse;
import Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class SupplierDao {
    public static boolean save(Supplier tedarikci) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(tedarikci);
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

    public static boolean update(Supplier tedarikci) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(tedarikci);
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

            Supplier role = em.find(Supplier.class, id);
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

    public static Supplier findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Supplier.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Supplier> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Supplier> query =
                    em.createQuery("FROM Supplier", Supplier.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static List<Supplier> findByWareHouseId(long depo_id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT w FROM Supplier w WHERE w.depo.id = :depo_id",
                    Supplier.class)
                    .setParameter("depo_id", depo_id)
                    .getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static Supplier getByName(String supplier_name) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT w FROM Supplier w WHERE w.name = :supplier_name",
                    Supplier.class)
                    .setParameter("supplier_name", supplier_name)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
