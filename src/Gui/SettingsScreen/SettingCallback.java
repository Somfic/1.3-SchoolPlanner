package Gui.SettingsScreen;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SettingCallback {
    void onSettingChange(ScheduleSettings newSettings);

    class ScheduleSettings {
        int speed;
        Color color;
        int classBlockLength;
        LocalTime time;
        Pair<Integer, Integer> fastBreak;
        Pair<Integer, Integer> lunchBreak;

        public ScheduleSettings(int speed, Color color, int classBlockLength, Pair<Integer, Integer> fastBreak, Pair<Integer, Integer> lunchBreak, LocalTime time) {
            this.speed = speed;
            this.color = color;
            this.classBlockLength = classBlockLength;
            this.fastBreak = fastBreak;
            this.lunchBreak = lunchBreak;
            this.time = time;
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
        public LocalTime getStartingTime() {
            return time;
        }
    }
}
