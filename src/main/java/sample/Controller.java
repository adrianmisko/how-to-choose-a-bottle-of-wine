package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane pane;
    @FXML
    private Label label;
    private List<JFXButton> buttons;

    private KieSession session;

    public Controller() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GridPane.setFillWidth(label, true);
        GridPane.setFillHeight(label, true);
        buttons = new ArrayList<>();

        KieServices ks = KieServices.Factory.get();
        BasicConfigurator.configure();
        Logger.getLogger(Main.class).setLevel(Level.OFF);
        KieContainer kContainer = ks.getKieClasspathContainer();
        session = kContainer.newKieSession("ksession-rules");
        session.setGlobal("controller", this);
        session.fireAllRules();
    }

    public void getNewQuestion(Question question) {

        label.setText(question.getText());

        pane.getChildren().removeAll(buttons);
        buttons.clear();

        for (int i = 0; i < question.getPossibleAnswers().size(); i++) {
            String possibleAnswer = question.getPossibleAnswers().get(i);
            JFXButton button = new JFXButton(possibleAnswer);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            button.getStyleClass().add("button-raised");
            button.setOnAction(action -> {
                session.insert(new Answer(possibleAnswer, question.getText()));
                session.fireAllRules();
            });
            buttons.add(button);
            pane.add(button, 1, i*3 + 3, 6, 2);
        }

    }

}
