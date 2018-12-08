package sample;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Question {

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private List<String> possibleAnswers;

    public Question(String text) {
        this.text = text;
        possibleAnswers = new ArrayList<>();
    }

}
