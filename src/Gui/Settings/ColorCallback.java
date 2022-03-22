package Gui.Settings;

import javafx.scene.paint.Color;

public interface ColorCallback {
    void onThemeColorChange(Color color);
    void onTextBrightnessChange(boolean darkness);
}
