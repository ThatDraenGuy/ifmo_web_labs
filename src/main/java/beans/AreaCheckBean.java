package beans;

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

    public void shoot() {
        for (Quadrant quadrant : quadrantsBean.getQuadrants()) {
            boolean res = quadrant.checkHit(chooserBean.getX(), chooserBean.getY(), chooserBean.getR());
            if (res) {
                System.out.println("hit!");
                return;
            }
        }
        System.out.println("miss!");
    }
}
