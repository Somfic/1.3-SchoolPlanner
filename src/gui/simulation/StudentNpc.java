package gui.simulation;

import data.*;
import data.Student;
import io.FileManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class StudentNpc extends Npc {
    private final Student student;
    private final String studentGroup;

    public StudentNpc(Student student, String studentGroup) {
        super(student);
        this.student = student;
        this.studentGroup = studentGroup;

        try {
            BufferedImage sprites[] = new BufferedImage[2];
            Random random = new Random();
            int colorOffset = 36 * random.nextInt(3);
            if (student.getGender() == Gender.MALE) {
                sprites[0] = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(41 + colorOffset, 0, 15, 34);
                sprites[1] = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(41 + colorOffset, 34, 15, 34);
            } else {
                sprites[0] = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(56 + colorOffset, 0, 21, 34);
                sprites[1] = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(56 + colorOffset, 34, 21, 34);
            }
            setSprites(sprites);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void calculateTarget(Schedule schedule, int period, MapInfo mapInfo) {
        if(this.target != null) {
            return;
        }

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


        if (currentPeriod == null) {
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
