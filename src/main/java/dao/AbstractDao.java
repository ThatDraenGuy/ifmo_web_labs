package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {
    @PersistenceContext
    EntityManager entityManager;

    private final Class<T> clazz;

    protected AbstractDao(Class<T> tClass) {
        this.clazz=tClass;
    }
    @Override
    @Transactional
    public void save(T t) {
        entityManager.persist(t);
        entityManager.flush();
    }


    @Override
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        query.select(query.from(clazz));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean isEmpty() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        query.select(criteriaBuilder.count(query.from(clazz)));
        return entityManager.createQuery(query).getSingleResult().equals(0L);
    }

    @Override
    @Transactional
    public void clear() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> delete = criteriaBuilder.createCriteriaDelete(clazz);
        delete.from(clazz);
        entityManager.createQuery(delete).executeUpdate();
    }

    protected CriteriaQuery<T> criteriaSelectEqual(Object value, String columnName) {
        var cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cBuilder.createQuery(clazz);
        Root<T> entity = q.from(clazz);
        return q.select(entity).where(cBuilder.equal(entity.get(columnName), value));
    }

}
