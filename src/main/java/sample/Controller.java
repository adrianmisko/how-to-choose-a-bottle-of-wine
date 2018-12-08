package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Controller {

    @FXML
    private JFXButton button;
    private KieSession session;

    public Controller() {
        KieServices ks = KieServices.Factory.get();
        BasicConfigurator.configure();
        Logger.getLogger(Main.class).setLevel(Level.OFF);
        KieContainer kContainer = ks.getKieClasspathContainer();
        session = kContainer.newKieSession("ksession-rules");
        session.setGlobal("controller", this);
        session.fireAllRules();
    }

    @FXML
    private void buttonClick() {
        session.insert(new Fact("Personal use"));
        session.fireAllRules();
    }

}
