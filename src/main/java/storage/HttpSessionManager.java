package storage;

import info.AttemptInfo;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class HttpSessionManager<T> implements HistoryManager<T>{
    private final String id = "history";
    private HttpSession session;

    public HttpSessionManager(HttpSession session) {
        this.session = session;
    }
    public void updateSession(HttpSession session) {
        if (! this.session.equals(session)) this.session = session;
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
