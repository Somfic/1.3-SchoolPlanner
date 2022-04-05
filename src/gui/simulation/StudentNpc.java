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

public class StudentNpc extends Npc {
    private final Student student;
    private final String studentGroup;

    private Vector2 seat;

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
    Vector2 getNextMove(Schedule schedule, int period, MapInfo mapInfo) {
        // Get the current period
        ScheduleItem currentPeriod = null;

        for (ScheduleItem item : schedule.getItems()) {
            if (item.getStartPeriod() <= period && item.getEndPeriod() >= period) {
                for (StudentGroup studentGroup : item.getStudentGroups()) {
                    if (studentGroup.getName().equals(this.studentGroup)) {
                        currentPeriod = item;
                        break;
                    }
                }
                break;
            }
        }

        // The student is not in a class
        if (currentPeriod == null) {
            return new Vector2(0, 0);
        }

        // Get a seat
        if(seat == null) {
            seat = mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getSeat();
        }

        return seat;

    }

    @Override
    void giveUpSeat() {
        this.seat = null;
    }

    public Student getStudent() {
        return student;
    }

}
