package Gui.SettingsScreen;

import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class ColorSelector {
    private ColorCallback callback;
    private HBox hBox;
    private Color color;
    private ColorPicker colorPicker;
    private Rectangle rectangle;

    public ColorSelector(ColorCallback callback) {
        this.callback = callback;
        this.colorPicker = new ColorPicker();
        this.color = Color.AQUAMARINE;
        this.colorPicker.setValue(color);
        this.rectangle = new Rectangle(50, 50);
        this.rectangle.setFill(colorPicker.getValue());

        this.colorPicker.setOnAction(event -> {
            color = colorPicker.getValue();
            rectangle.setFill(color);
            callback.onColourChange(color);
        });

        this.hBox = new HBox(rectangle, colorPicker);
    }

    public HBox getContent() {
        return this.hBox;
    }
}
