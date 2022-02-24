package Gui.SettingsScreen;

import javafx.scene.paint.Color;
import javafx.util.Pair;

public interface SettingCallback {
    void onSettingChange(ScheduleSettings newSettings);

    class ScheduleSettings {
        int speed;
        Color color;
        int classBlockLength;
        Pair<Integer, Integer> fastBreak;
        Pair<Integer, Integer> lunchBreak;

        public ScheduleSettings(int speed, Color color, int classBlockLength, Pair<Integer, Integer> fastBreak, Pair<Integer, Integer> lunchBreak) {
            this.speed = speed;
            this.color = color;
            this.classBlockLength = classBlockLength;
            this.fastBreak = fastBreak;
            this.lunchBreak = lunchBreak;
        }

        public int getSpeed() {
            return speed;
        }

        public Color getColor() {
            return color;
        }

        public int getClassBlockLength() {
            return classBlockLength;
        }

        public Pair<Integer, Integer> getFastBreak() {
            return fastBreak;
        }

        public Pair<Integer, Integer> getLunchBreak() {
            return lunchBreak;
        }
    }
}
