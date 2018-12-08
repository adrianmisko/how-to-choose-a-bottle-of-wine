package sample;


import lombok.Getter;
import lombok.Setter;

public class Answer {

    @Getter
    @Setter
    private String text;

    public Answer(String text) {
        this.text = text;
    }

}
