package beans;

import domain.AttemptInfo;
import domain.quadrants.Quadrant;
import jakarta.enterprise.context.SessionScoped;
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

    public void shoot() {
        AttemptInfo attemptInfo = quadrantsBean.doCheck(chooserBean.getX(),chooserBean.getY(),chooserBean.getR());
        historyBean.add(attemptInfo);
    }

}
