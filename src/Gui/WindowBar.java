package Gui;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class WindowBar {
    private double mousePressedX, mousePressedY;
    private Pane headerPane;
    private HBox hBox;
    private Button close, minimize, maximize;
    private Circle circle;
    private Stage stage;
    private String buttonStyle = "-fx-background-color: ";
    private String paneStyle = "-fx-text-alignment: center; -fx-background-color: ";
    private int buttonSize = 5;
    private double r = 7.5;

    public WindowBar(Stage stage) {
        this.stage = stage;

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
        this.hBox = new HBox(close, minimize, maximize);
        this.hBox.setSpacing(5);

        //Image

        //Pane
        this.headerPane = new Pane(hBox);

        //Dragging ability
        headerPane.addEventFilter(MouseEvent.MOUSE_PRESSED, (EventHandler<MouseEvent>) mouseEvent -> {
            mousePressedX = mouseEvent.getX();
            mousePressedY = mouseEvent.getY();
        });
        headerPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, (EventHandler<MouseEvent>) mouseEvent -> {
            double crrX = mouseEvent.getScreenX();
            double crrY = mouseEvent.getScreenY();
            this.stage.setX(crrX - mousePressedX);
            this.stage.setY(crrY - mousePressedY);
        });
        //Button functionality
        close.setOnMouseClicked((ActionEvent) -> {
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

    public Pane getContent() {
        return this.headerPane;
    }
}