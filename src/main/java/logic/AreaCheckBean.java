package logic;

import jakarta.annotation.PostConstruct;
import monitoring.HitPercentage;
import monitoring.HitPercentageMBean;
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
    private HitPercentageMBean hitPercentageMBean;
    @PostConstruct
    private void init() {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName shots = new ObjectName("monitoring:type = ShotsMBean");
            ObjectName hitPercentage = new ObjectName("monitoring:type = HitPercentageMBean");
            shotsMBean = new Shots();
            hitPercentageMBean = new HitPercentage();
            mBeanServer.registerMBean(shotsMBean, shots);
            mBeanServer.registerMBean(hitPercentageMBean, hitPercentage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shoot(double x, double y, double r) {
        AttemptInfo attemptInfo = quadrantsBean.doCheck(x,y,r);
        shotsMBean.addShot(attemptInfo);
        hitPercentageMBean.addShot(attemptInfo);
        historyBean.add(attemptInfo);
    }

}
