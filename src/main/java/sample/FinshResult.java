package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FinshResult {

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private Image img;

    public FinshResult(String text, String imgPath) {
        this.text = text;
        if (imgPath == null)
            this.img = null;
        else
            loadImage(imgPath);
    }

    private void loadImage(String imgPath) {

        String path = System.getProperty("user.dir");
        path = path + "/src/main/resources/pictures/" + imgPath;

        System.out.printf(path);
        File file = new File(path);
        Image im = new Image(file.toURI().toString());
        this.img = im;
    }

}
