package logic;

import jakarta.annotation.PostConstruct;
import monitoring.ShotsMBean;
import domain.AttemptInfo;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import monitoring.Shots;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;

@Named
@SessionScoped
public class AreaCheckBean implements Serializable {
    @Inject
    QuadrantsBean quadrantsBean;
    @Inject
    HistoryBean historyBean;

    private ShotsMBean shotsMBean;
    @PostConstruct
    private void init() {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName shots = new ObjectName("monitoring:type = ShotsMBean");
            shotsMBean = new Shots();
            mBeanServer.registerMBean(shotsMBean, shots);
            System.out.println("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void shoot(double x, double y, double r) {
        AttemptInfo attemptInfo = quadrantsBean.doCheck(x,y,r);
        shotsMBean.addShot(attemptInfo);
        historyBean.add(attemptInfo);
    }

}
