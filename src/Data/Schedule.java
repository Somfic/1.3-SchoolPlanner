package Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final List<ScheduleItem> items;

    public Schedule() {
        this.items = new ArrayList<>();
    }

    public void add(ScheduleItem item) {
        items.add(item);
        System.out.println(items.size());
    }

    public void add(List<ScheduleItem> items) {
        for (ScheduleItem item : items) {
            add(item);
        }
    }
    public void reset(){
        items.clear();
        System.out.println(items.size());
    }
    public void remove(ScheduleItem scheduleItem) {
        if (items.size() <= 1) {
            items.clear();
        }
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).getClassroom().getName().equals(scheduleItem.getClassroom().getName()) && items.get(i).getStartPeriod() == scheduleItem.getStartPeriod()) {
                items.remove(i);
                break;
            }
            System.out.println(items.size());
        }
    }

    public List<ScheduleItem> getItems() {
        return items;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Schedule fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, Schedule.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
