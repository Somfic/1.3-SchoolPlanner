package Gui.SettingsScreen;

import javafx.util.Pair;

public interface BreakTimeCallback {
    void onBreakTimeChange(Pair<Integer, Integer> time);
}
