package Gui.Settings;

import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class ColorSelector {
    private ColorCallback callback;
    private HBox hBox;
    private ColorPicker colorPicker;
    private Rectangle rectangle;
    private Color colorCurrent;
    private Color colorMemory;

    public ColorSelector(ColorCallback callback) {
        this.callback = callback;
        this.colorPicker = new ColorPicker();
        this.colorCurrent = Color.INDIANRED;
        this.colorMemory = colorCurrent;
        this.colorPicker.setValue(colorCurrent);
        this.rectangle = new Rectangle(50, 50);
        this.rectangle.setFill(colorPicker.getValue());

        this.colorPicker.setOnAction(event -> {
            colorCurrent = colorPicker.getValue();
            rectangle.setFill(colorCurrent);
        });

        this.hBox = new HBox(rectangle, colorPicker);
        this.hBox.setSpacing(15);
    }

    public void confirm() {
        this.colorMemory = this.colorCurrent;
        callback.onThemeColorChange(colorCurrent);
        /*
         * The higher the number returned on the .getBrightness() method, the brighter the color
         * Return true if the theme color is "bright", return false if the theme color is "dark"
         */
        callback.onTextBrightnessChange(colorCurrent.getBrightness() < 0.4);
    }

    public void cancel() {
        this.colorCurrent = this.colorMemory;
        this.colorPicker.setValue(colorCurrent);
        this.rectangle.setFill(colorCurrent);
    }

    public HBox getContent() {
        return this.hBox;
    }
}
