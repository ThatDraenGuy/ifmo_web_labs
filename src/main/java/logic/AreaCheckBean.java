package logic;

import ui.ChooserBean;
import domain.AttemptInfo;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class AreaCheckBean implements Serializable {
    @Inject
    QuadrantsBean quadrantsBean;
    @Inject
    HistoryBean historyBean;

    public void shoot(double x, double y, double r) {
        AttemptInfo attemptInfo = quadrantsBean.doCheck(x,y,r);
        historyBean.add(attemptInfo);
    }

}
