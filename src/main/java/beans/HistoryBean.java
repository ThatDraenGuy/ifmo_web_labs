package beans;

import domain.AttemptInfo;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
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
//
//    @Inject
//    EntityManager entityManager;


    public void add(AttemptInfo attemptInfo) {
//        entityManager.persist(attemptInfo);
        attempts.add(attemptInfo);
        System.out.println(attemptInfo);
    }

    public List<AttemptInfo> getAttempts() {
        return attempts;
    }

    public boolean isEmpty() {
        return attempts.isEmpty();
    }

    public AttemptInfo getLast() {
        if (attempts.size()!=0) {
            return attempts.get(attempts.size()-1);
        }
        return AttemptInfo.empty();
    }

    public void clear() {
        attempts.clear();
    }
}
