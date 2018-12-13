package sample;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.io.File;


public class Result {

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private Image img;

    public Result(String text, String imgPath) {
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
