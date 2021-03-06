package gui.components;

import gui.settings.SettingView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WindowBar {
    private double mousePressedX, mousePressedY;
    private Stage stage;
    private SettingView settings;
    private BorderPane headerPane;
    private HBox buttonBox;
    private HBox titleBox;
    private ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/Icon.png")));
    private Button close, minimize, maximize;
    private Circle circle;
    private String buttonStyle = "-fx-background-color: ";
    private String paneStyle = "-fx-text-alignment: center; -fx-background-color: ";
    private int buttonSize = 5;
    private double r = 7.5;

    public WindowBar(Stage stage, SettingView settings) {
        this.stage = stage;
        this.settings = settings;

        //Buttons
        this.close = new Button();
        this.minimize = new Button();
        this.maximize = new Button();
        this.circle = new Circle(r);
        close.setShape(this.circle);
        this.minimize.setShape(this.circle);
        this.maximize.setShape(this.circle);
        close.setMinSize(2 * r, 2 * r);
        close.setMaxSize(2 * r, 2 * r);
        minimize.setMinSize(2 * r, 2 * r);
        minimize.setMaxSize(2 * r, 2 * r);
        maximize.setMinSize(2 * r, 2 * r);
        maximize.setMaxSize(2 * r, 2 * r);
        this.close.setStyle(buttonStyle + "#E23838");
        this.minimize.setStyle(buttonStyle + "#FFB900");
        this.maximize.setStyle(buttonStyle + "#61BB46");
        this.buttonBox = new HBox(maximize, minimize, close);
        this.buttonBox.setSpacing(3);

        //Image + Title
        Text text = new Text("/School Planner");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        text.setFill(Color.BLACK);
        this.image.autosize();
        this.titleBox = new HBox(image, text);
        this.titleBox.setSpacing(2);

        //Pane
        this.headerPane = new BorderPane();
        this.headerPane.setRight(buttonBox);
        this.headerPane.setLeft(titleBox);
        this.headerPane.autosize();
        update("#FFFFFF");

        //Dragging ability
        headerPane.addEventFilter(MouseEvent.MOUSE_PRESSED, (EventHandler<MouseEvent>) mouseEvent -> {
            mousePressedX = mouseEvent.getX();
            mousePressedY = mouseEvent.getY();
        });
        headerPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, (EventHandler<MouseEvent>) mouseEvent -> {
            if (stage.isMaximized()) stage.setMaximized(false);
            double crrX = mouseEvent.getScreenX();
            double crrY = mouseEvent.getScreenY();
            this.stage.setX(crrX - mousePressedX);
            this.stage.setY(crrY - mousePressedY);
        });

        //Button functionality
        close.setOnMouseClicked((ActionEvent) -> {
            settings.save();
            stage.close();
        });
        minimize.setOnMouseClicked((ActionEvent) -> {
            stage.setIconified(true);
        });
        maximize.setOnMouseClicked((ActionEvent) -> {
            if (!stage.isMaximized()) {
                stage.setMaximized(true);
            } else {
                stage.setMaximized(false);
            }
        });
    }

    public void update(String hexCodeColor) {
        this.headerPane.setStyle("-fx-background-color: " + hexCodeColor);
    }

    public BorderPane getContent() {
        return this.headerPane;
    }
}