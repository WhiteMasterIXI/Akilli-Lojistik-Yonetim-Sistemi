package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ShoppingSystem.Products.AllowedComponent;
import ShoppingSystem.Products.RequestProductComponent;
import Utils.JPAUtil;

public class RequestProductComponentDao {

    public static boolean save(RequestProductComponent pc) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(pc);
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

    public static boolean update(RequestProductComponent pc) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(pc);
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

    public static boolean  delete(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            RequestProductComponent pc = em.find(RequestProductComponent.class, id);
            if (pc != null) {
                em.remove(pc);
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

    public static RequestProductComponent findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(RequestProductComponent.class, id);
        } finally {
            em.close();
        }
    }

    public static List<RequestProductComponent> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<RequestProductComponent> query =
                    em.createQuery("FROM ProductComponent", RequestProductComponent.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<RequestProductComponent> findByParent(long productId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT pc FROM RequestProductComponent pc WHERE pc.parent.id = :pid",
                    RequestProductComponent.class)
                    .setParameter("pid", productId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public static List<RequestProductComponent> findByChild(long componentId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT pc FROM RequestProductComponent pc WHERE pc.child.id = :cid",
                    RequestProductComponent.class)
                    .setParameter("cid", componentId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public RequestProductComponent findRelation(int parentId, long childId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT pc FROM RequestProductComponent pc WHERE pc.parent.id = :pid AND pc.child.id = :cid",
                    RequestProductComponent.class)
                    .setParameter("pid", parentId)
                    .setParameter("cid", childId)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }
    
    public static void deleteByParentId(int parentId) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.createQuery(
                "DELETE FROM RequestProductComponent pc WHERE pc.parent.id = :pid"
            )
            .setParameter("pid", parentId)
            .executeUpdate();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}