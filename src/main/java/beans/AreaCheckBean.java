package beans;

import domain.AttemptInfo;
import domain.quadrants.Quadrant;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class AreaCheckBean implements Serializable {
    @Inject
    ChooserBean chooserBean;
    @Inject
    QuadrantsBean quadrantsBean;
    @Inject
    HistoryBean historyBean;

    public void shoot(double x, double y, double r) {
        AttemptInfo attemptInfo = quadrantsBean.doCheck(x,y,r);
        historyBean.add(attemptInfo);
    }

}
