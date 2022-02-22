package Gui.SettingsScreen;

import javafx.scene.paint.Color;
import javafx.util.Pair;

public interface SettingCallback {
    void onSettingChange(int speed, Color color, int classBlockLength, Pair<Integer, Integer> fastBreak, Pair<Integer, Integer> lunchBreak);
}
