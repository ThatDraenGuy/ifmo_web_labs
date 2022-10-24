package beans;

import domain.AttemptInfo;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("historyBean")
@SessionScoped
public class HistoryBean implements Serializable {
//    @Getter
    List<AttemptInfo> attempts;

    @PostConstruct
    private void init() {
        attempts = new ArrayList<>();
    }

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void add(AttemptInfo attemptInfo) {
        entityManager.persist(attemptInfo);
        entityManager.flush();
//        attempts.add(attemptInfo);
        System.out.println(attemptInfo);
    }

    public List<AttemptInfo> getAttempts() {
        TypedQuery<AttemptInfo> query = entityManager.createQuery("SELECT e FROM AttemptInfo e", AttemptInfo.class);
        return query.getResultList();
    }

    public boolean isEmpty() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(*) FROM AttemptInfo", Long.class);
        return query.getResultList().get(0).equals(0L);
    }

    @Transactional
    public void clear() {
        Query query = entityManager.createQuery("DELETE from AttemptInfo");
        query.executeUpdate();
//        attempts.clear();
    }
}
