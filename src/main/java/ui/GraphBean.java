package ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import logic.AreaCheckBean;
import monitoring.IntervalMBean;
import monitoring.IntervalMBeanImpl;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;

@Named("graphBean")
@SessionScoped
public class GraphBean implements Serializable {
    @Inject
    AreaCheckBean areaCheckBean;
    @Inject
    ChooserBean chooserBean;

    private IntervalMBean intervalMBean;

    @PostConstruct
    private void init() {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName interval = new ObjectName("monitoring:type=basic,name=interval");
            intervalMBean = new IntervalMBeanImpl();
            mBeanServer.registerMBean(intervalMBean, interval);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void handleClick(double base, double graphBase) {
        intervalMBean.handleClick();
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
