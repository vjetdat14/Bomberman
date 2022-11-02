package uet.oop.bomberman.View;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

import java.net.URISyntaxException;
import java.util.Objects;

public class BT extends Button {
    
    private final Font font;
    private final int x=300;
    private final int y=50;
    Image logo;

    {
        try {
            logo = new Image(Objects.requireNonNull(getClass().getResource("/play.png")).toURI().toString(),40,40,true,true);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    ImageView lg = new ImageView(logo);

    {
        try {
            font = Font.loadFont(Objects.requireNonNull(getClass().getResource("/font/upheavtt.ttf")).toURI().toString(), 22);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    private final String style1 = "-fx-background-color: #038BF8 ; -fx-background-image: url('/res/red_button_pressed.png');";
    private final String style2="-fx-background-color: #039BE5; -fx-background-image: url('/res/red_button.png');";

    public BT(String text) {
        setText(text);
        setGraphic(lg);
        setBTFont();
        setPrefHeight(y);
        setPrefWidth(x);
        setStyle(style2);
        click();

    }

    private void setBTFont() {
        setFont(font);
    }

    private void setBTStyle1() {
        setStyle(style1);
        setPrefHeight(y+4);
        setLayoutX(getLayoutX()+4);
    }
    private void setBTStyle2() {
        setStyle(style2);
        setPrefHeight(y);
        setLayoutX(getLayoutX()-4);
    }

    private void click() {
        setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                setBTStyle1();
            }
        });
        setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                setBTStyle2();
            }
        });

        setOnMouseEntered(mouseEvent -> setEffect(new DropShadow()));

        setOnMouseExited(mouseEvent -> setEffect(null));
        }
}
