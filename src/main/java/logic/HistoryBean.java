package logic;

import dao.AttemptInfoDao;
import domain.AttemptInfo;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;


import java.io.Serializable;

import java.util.Comparator;
import java.util.List;

@Named("historyBean")
@SessionScoped
public class HistoryBean implements Serializable {

    @Inject
    AttemptInfoDao attemptInfoDao;

    public void add(AttemptInfo attemptInfo) {
        attemptInfoDao.save(attemptInfo);
    }

    public List<AttemptInfo> getAttempts() {
        List<AttemptInfo> history = attemptInfoDao.getAll();
        history.sort((o1, o2) -> o2.getCurrTime().compareTo(o1.getCurrTime()));
        return history;
    }

    public boolean isEmpty() {
        return attemptInfoDao.isEmpty();
    }

    @Transactional
    public void clear() {
        attemptInfoDao.clear();
    }
}
