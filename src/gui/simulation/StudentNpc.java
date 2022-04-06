package gui.simulation;

import data.*;
import data.Student;
import io.FileManager;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentNpc extends Npc {
    private final Student student;
    private final String studentGroup;

    public StudentNpc(Student student, String studentGroup) {
        super(student);
        this.student = student;
        this.studentGroup = studentGroup;

        try {
            BufferedImage sprite;
            if (student.getGender() == Gender.MALE) {
                sprite = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(41, 0, 15, 34);
            } else {
                sprite = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(56, 0, 21, 34);
            }
            setSprite(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void calculateTarget(Schedule schedule, int period, MapInfo mapInfo) {
        if (this.target != null) {
            return;
        }

        // Get the current period
        ScheduleItem currentPeriod = null;

        List<ScheduleItem> applicablePeriods = new ArrayList<>();

        for (ScheduleItem item : schedule.getItems()) {
            for (StudentGroup studentGroup : item.getStudentGroups()) {
                if (studentGroup.getName().equals(this.studentGroup)) {
                    applicablePeriods.add(item);
                }
            }
        }

        for (ScheduleItem item : applicablePeriods) {
            if (item.getStartPeriod() <= period && item.getEndPeriod() >= period) {
                currentPeriod = item;
                break;
            }
        }

        if (currentPeriod == null) {
            if(applicablePeriods.size() > 0) {
                ScheduleItem firstPeriod = applicablePeriods.get(0);
                ScheduleItem lastPeriod = applicablePeriods.get(applicablePeriods.size() - 1);

                if(firstPeriod.getStartPeriod() > period || lastPeriod.getEndPeriod() < period) {
                    target = mapInfo.getSpawnPoints().get((int)(Math.floor(Math.random() * mapInfo.getSpawnPoints().size())));
                    return;
                }
            }


            // The student is not in a class
            this.target = mapInfo.getBreakArea().getSeat();
        } else {
            this.target = mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getSeat();
        }
    }

    public Student getStudent() {
        return student;
    }

}
