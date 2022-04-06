package gui.settings;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.time.LocalTime;

public interface SettingCallback {
    void onSettingChange(ScheduleSettings newSettings);

    @JsonDeserialize(as = SettingCallback.ScheduleSettings.class)
    class ScheduleSettings implements Serializable {
        private int speed;
        private Color color;
        private boolean textBrightness;
        private int classBlockLength;
        private LocalTime startTime;
        private int fastBreakTime;
        private int fastBreakLength;
        private int lunchBreakTime;
        private int lunchBreakLength;

        public ScheduleSettings() {
        }

        public ScheduleSettings(int speed, Color color, boolean textBrightness, int classBlockLength, int fastBreakTime, int fastBreakLength, int lunchBreakTime, int lunchBreakLength, LocalTime startTime) {
            this.speed = speed;
            this.color = color;
            this.textBrightness = textBrightness;
            this.classBlockLength = classBlockLength;
            this.fastBreakTime = fastBreakTime;
            this.fastBreakLength = fastBreakLength;
            this.lunchBreakTime = lunchBreakTime;
            this.lunchBreakLength = lunchBreakLength;
            this.startTime = startTime;
        }

        public int getSpeed() {
            return speed;
        }

        public Color getColor() {
            return color;
        }

        public boolean isTextBrightness() {
            return textBrightness;
        }

        public int getClassBlockLength() {
            return classBlockLength;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public int getFastBreakTime() {
            return fastBreakTime;
        }

        public int getFastBreakLength() {
            return fastBreakLength;
        }

        public int getLunchBreakTime() {
            return lunchBreakTime;
        }

        public int getLunchBreakLength() {
            return lunchBreakLength;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setTextBrightness(boolean textBrightness) {
            this.textBrightness = textBrightness;
        }

        public void setClassBlockLength(int classBlockLength) {
            this.classBlockLength = classBlockLength;
        }

        public void setTime(LocalTime time) {
            this.startTime = time;
        }

        public void setFastBreakTime(int fastBreakTime) {
            this.fastBreakTime = fastBreakTime;
        }

        public void setFastBreakLength(int fastBreakLength) {
            this.fastBreakLength = fastBreakLength;
        }

        public void setLunchBreakTime(int lunchBreakTime) {
            this.lunchBreakTime = lunchBreakTime;
        }

        public void setLunchBreakLength(int lunchBreakLength) {
            this.lunchBreakLength = lunchBreakLength;
        }
    }
}
