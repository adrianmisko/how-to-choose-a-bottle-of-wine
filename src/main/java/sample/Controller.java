package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
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
        buttons = new ArrayList<>();
        label.setMaxWidth(Double.MAX_VALUE);
        pane.setPadding(new Insets(45, 45, 45, 45));

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
        pane.getRowConstraints().subList(3, pane.getRowConstraints().size()).clear();
        pane.setVgap(45.0);
        buttons.clear();
        int numOfAnswers = question.getPossibleAnswers().size();
        for (int i = 0; i < numOfAnswers; i++) {
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
            if (i >= 2) {
                pane.setVgap(22.5);
                RowConstraints rc = new RowConstraints();
                rc.setVgrow(Priority.SOMETIMES);
                rc.setMinHeight(10.0);
                rc.setPrefHeight(30.0);
                pane.getRowConstraints().add(rc);
            }
            pane.add(button, 0, i+1, 1, 1);
        }

    }

    public void getFinishResult(FinshResult result) {

        label.setText(result.getText());
        pane.setPadding(new Insets(5, 0, 0, 0));
        label.setContentDisplay(ContentDisplay.TOP);
        pane.getChildren().removeAll(buttons);
        buttons.clear();


        if (result.getImg() != null) {
            ImageView img = new ImageView(result.getImg());
            img.setFitHeight(label.getHeight() * 2 + label.getLayoutY());
            img.setPreserveRatio(true);
            pane.add(new StackPane(), 0, 1, 1, 2);
            StackPane sp = (StackPane)pane.getChildren().get(1);
            sp.getChildren().add(img);
            sp.setAlignment(img, Pos.CENTER);

        }

    }


}
