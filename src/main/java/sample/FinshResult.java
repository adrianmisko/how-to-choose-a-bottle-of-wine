package sample;

import lombok.Getter;
import lombok.Setter;


public class FinshResult {

    @Getter
    @Setter
    private String text;

    public FinshResult(String text) {
        this.text = text;
    }
}
