package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("graphBean")
@SessionScoped
public class GraphBean implements Serializable {
    @Inject
    AreaCheckBean areaCheckBean;
    @Inject
    ChooserBean chooserBean;

    public void handleClick(double base, double graphBase) {
        double modifier = graphBase / base;
        Double r = chooserBean.getR();

        String xStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        double xFrac = Double.parseDouble(xStr);
        double x = round(xFrac * modifier * r);

        String yStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");
        double yFrac = Double.parseDouble(yStr);
        double y = round(yFrac * modifier * r);

        areaCheckBean.shoot(x,y,r);
    }

    private double round(double value) {
        return Math.round(value*100) / 100.0;
    }
}
