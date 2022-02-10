package Data;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final List<ScheduleItem> items;

    public Schedule() {
        this.items = new ArrayList<>();
    }

    public void add(ScheduleItem item) {
        items.add(item);
    }

    public void add(List<ScheduleItem> items) {
        for (ScheduleItem item : items) {
            add(item);
        }
    }
}
