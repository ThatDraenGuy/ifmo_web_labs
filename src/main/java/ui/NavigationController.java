package ui;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("navigationController")
@RequestScoped
public class NavigationController implements Serializable {
    public String goToMain() {return "";}
    public String goToIndex() {return "";}
}
