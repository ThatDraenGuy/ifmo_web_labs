package dao;

import domain.AttemptInfo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.criteria.CriteriaQuery;

@ApplicationScoped
public class AttemptInfoDao extends AbstractDao<AttemptInfo> {
    protected AttemptInfoDao() {
        super(AttemptInfo.class);
    }

    @Override
    public AttemptInfo get(long id) {
        CriteriaQuery<AttemptInfo> query = criteriaSelectEqual(id, "id");
        return super.entityManager.createQuery(query).getSingleResult();
    }
}
