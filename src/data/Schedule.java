package data;

import logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.media.jfxmediaimpl.HostUtils;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final List<ScheduleItem> items;

    public Schedule() {
        this.items = new ArrayList<>();
    }

    public void add(ScheduleItem item) {
        validate(item);

        if(item.getEndPeriod() - item.getStartPeriod() >= 0 && item.getEndPeriod() >= 1 && item.getEndPeriod()<= 10 &&  item.getStartPeriod() >= 1 && item.getStartPeriod() <= 10){
            items.add(item);
        }
    }

    private void validate(ScheduleItem item) {
        for (int i = 0; i <items.size(); i++) {
            if(item.getClassroom().getName().equals(items.get(i).getClassroom().getName()) ){
                if((item.getStartPeriod() >= items.get(i).getStartPeriod()
                        && item.getStartPeriod() <= items.get(i).getEndPeriod())
                        || (item.getEndPeriod() >= items.get(i).getStartPeriod()
                        && item.getEndPeriod()<=items.get(i).getEndPeriod())) {
                    items.remove(i);
                    validate(item);
                }
            }
        }
    }


    public void add(List<ScheduleItem> items) {
        for (ScheduleItem item : items) {
            add(item);
        }
    }
    public void reset(){
        items.clear();
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
        }
    }

    public List<ScheduleItem> getItems() {
        return items;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            Logger.warn(e, "Failed to serialize schedule");
            return null;
        }
    }

    public static Schedule fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, Schedule.class);
        } catch (Exception e) {
            Logger.warn(e, "Failed to deserialize schedule");
            return null;
        }
    }
}
