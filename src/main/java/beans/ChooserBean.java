package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named("chooserBean")
@SessionScoped
public class ChooserBean implements Serializable {
    @Getter
    @Setter
    private Double x;
    @Getter
    @Setter
    private Double y;
    @Getter
    @Setter
    private Double r;

    public void show() {
        System.out.println(x);
        System.out.println(y);
        System.out.println(r);
    }
}
