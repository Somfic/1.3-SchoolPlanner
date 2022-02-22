package Gui.SettingsScreen;

import javafx.util.Pair;

public interface BreakTimeCallback {
    void onFastBreakTimeChange(Pair<Integer, Integer> time);
    void onLunchBreakTimeChange(Pair<Integer, Integer> time);
}
