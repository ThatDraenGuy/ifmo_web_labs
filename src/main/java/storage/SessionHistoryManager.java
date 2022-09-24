package storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class SessionHistoryManager<T> implements HistoryManager<T>{
    private final String id = "history";
    private HttpSession session = null;

    public void updateSession(HttpSession session) {
        if (!session.equals(this.session)) this.session = session;
    }

    @Override
    public boolean isEmpty() {
        return history().isEmpty();
    }

    @Override
    public boolean isSet() {
        return session.getAttribute(id) != null;
    }
    private List<T> set() {
        List<T> newHistory = new ArrayList<>();
        session.setAttribute(id, newHistory);
        return newHistory;
    }

    @Override
    public void put(T element) {
        history().add(element);
    }

    @Override
    public List<T> get() {
        return history();
    }

    @Override
    public void clear() {
        history().clear();
    }


    private List<T> history() {
        if (!isSet()) set();
        try {
            return (List<T>) session.getAttribute(id);
        } catch (ClassCastException e) {
            return set();
        }
    }
}
