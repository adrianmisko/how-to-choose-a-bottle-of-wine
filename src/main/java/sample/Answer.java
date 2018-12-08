package sample;


import lombok.Getter;
import lombok.Setter;

public class Answer {

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private String questionText;

    public Answer(String text, String questionText) {
        this.text = text;
        this.questionText = questionText;
    }

}
