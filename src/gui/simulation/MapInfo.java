package gui.simulation;

import logging.Logger;
import org.dyn4j.geometry.Vector2;

import java.util.*;

public class MapInfo {
    private final List<SeatInfo> classRooms = new ArrayList<>();
    private final SeatInfo teacherArea = new SeatInfo("Teacher Area").setSeats(
            new Vector2(6, 7), new Vector2(3, 9), new Vector2(3, 10), new Vector2(7, 8), new Vector2(7, 9)
            , new Vector2(7, 10), new Vector2(7, 11), new Vector2(5, 12), new Vector2(6, 12));

    private final SeatInfo breakArea = new SeatInfo("Break Area").setSeats(
            new Vector2(4, 14), new Vector2(5, 14), new Vector2(6, 14), new Vector2(7, 14),
            new Vector2(9, 14), new Vector2(10, 14), new Vector2(11, 14), new Vector2(12, 14),
            new Vector2(14, 14), new Vector2(15, 14), new Vector2(16, 14), new Vector2(17, 14),
            new Vector2(35, 14), new Vector2(36, 14), new Vector2(23, 7), new Vector2(24, 7)
            , new Vector2(25, 7), new Vector2(23, 9), new Vector2(24, 9), new Vector2(25, 9), new Vector2(27, 7)
            , new Vector2(28, 7), new Vector2(29, 7), new Vector2(27, 9), new Vector2(28, 9), new Vector2(29, 9)
            , new Vector2(31, 7), new Vector2(32, 7), new Vector2(33, 7), new Vector2(31, 9), new Vector2(32, 9)
            , new Vector2(33, 9), new Vector2(35, 7), new Vector2(36, 7), new Vector2(37, 7), new Vector2(35, 9)
            , new Vector2(36, 9), new Vector2(37, 9), new Vector2(27, 10), new Vector2(28, 10), new Vector2(29, 10)
            , new Vector2(27, 12), new Vector2(28, 12), new Vector2(29, 12), new Vector2(31, 10), new Vector2(32, 10)
            , new Vector2(33, 10), new Vector2(31, 12), new Vector2(32, 12), new Vector2(33, 12), new Vector2(35, 10),
            new Vector2(36, 10), new Vector2(37, 10), new Vector2(35, 12), new Vector2(36, 12), new Vector2(37, 12),
            new Vector2(23, 2), new Vector2(25,2), new Vector2(27, 2));

    public MapInfo() {
        classRooms.add(new SeatInfo("Classroom 1")
                .setSeats(
                        new Vector2(13, 2), new Vector2(15, 2), new Vector2(17, 2),
                        new Vector2(13, 3), new Vector2(15, 3), new Vector2(17, 3),
                        new Vector2(13, 5), new Vector2(15, 5), new Vector2(17, 5),
                        new Vector2(13, 6), new Vector2(15, 6), new Vector2(17, 6))
                .setTeacherSeat(new Vector2(9, 2)));

        classRooms.add(new SeatInfo("Classroom 2")
                .setSeats(
                        new Vector2(13, 8), new Vector2(15, 8), new Vector2(17, 8),
                        new Vector2(13, 9), new Vector2(15, 9), new Vector2(17, 9),
                        new Vector2(13, 11), new Vector2(15, 11), new Vector2(17, 11),
                        new Vector2(13, 12), new Vector2(15, 12), new Vector2(17, 12))
                .setTeacherSeat(new Vector2(9, 8)));

        classRooms.add(new SeatInfo("Classroom 3")
                .setSeats(
                        new Vector2(10, 22), new Vector2(11, 22), new Vector2(13, 22), new Vector2(14, 22),
                        new Vector2(10, 24), new Vector2(11, 24), new Vector2(13, 24), new Vector2(14, 24),
                        new Vector2(10, 26), new Vector2(11, 26), new Vector2(13, 26), new Vector2(14, 26))
                .setTeacherSeat(new Vector2(11, 18)));

        classRooms.add(new SeatInfo("Classroom 4")
                .setSeats(
                        new Vector2(20, 22), new Vector2(21, 22), new Vector2(23, 22), new Vector2(24, 22),
                        new Vector2(20, 24), new Vector2(21, 24), new Vector2(23, 24), new Vector2(24, 24),
                        new Vector2(20, 26), new Vector2(21, 26), new Vector2(23, 26), new Vector2(24, 26))
                .setTeacherSeat(new Vector2(24, 18)));

        classRooms.add(new SeatInfo("Classroom 5")
                .setSeats(
                        new Vector2(26, 22), new Vector2(27, 22), new Vector2(29, 22), new Vector2(30, 22),
                        new Vector2(26, 24), new Vector2(27, 24), new Vector2(29, 24), new Vector2(30, 24),
                        new Vector2(26, 26), new Vector2(27, 26), new Vector2(29, 26), new Vector2(30, 26))
                .setTeacherSeat(new Vector2(27, 18)));

        classRooms.add(new SeatInfo("Classroom 6")
                .setSeats(
                        new Vector2(36, 22), new Vector2(37, 22), new Vector2(39, 22), new Vector2(40, 22),
                        new Vector2(36, 24), new Vector2(37, 24), new Vector2(39, 24), new Vector2(40, 24),
                        new Vector2(36, 26), new Vector2(37, 26), new Vector2(39, 26), new Vector2(40, 26))
                .setTeacherSeat(new Vector2(40, 18)));
    }

    public List<SeatInfo> getClassRooms() {
        return classRooms;
    }

    public SeatInfo getClassRoom(String name) {
        return classRooms.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    public SeatInfo getBreakArea() {
        return breakArea;
    }

    public SeatInfo getTeacherArea() {
        return teacherArea;
    }
}

class SeatInfo {
    private List<Vector2> availableSeats = new ArrayList<>();
    private List<Vector2> occupiedSeats = new ArrayList<>();

    private Vector2 teacherSeat;
    private final String name;

    public SeatInfo(String name) {
        this.name = name;
    }

    public Vector2 getSeat() {
        // Return a random available seat
        if (availableSeats.isEmpty()) {
            Logger.warn("No available seats in " + name);
            return new Vector2(5, 28);
        }

        Vector2 seat = availableSeats.remove(new Random().nextInt(availableSeats.size()));
        occupiedSeats.add(seat);

        return seat;
    }

    public Vector2 getTeacherSeat() {
        return teacherSeat;
    }

    public void resetSeats() {
        // Add all the occupied seats back to the available seats
        availableSeats.addAll(occupiedSeats);

        // Clear the occupied seats
        occupiedSeats.clear();
    }

    public SeatInfo setTeacherSeat(Vector2 teacherSeat) {
        this.teacherSeat = teacherSeat;
        return this;
    }

    public SeatInfo setSeats(List<Vector2> availableSeats) {
        this.availableSeats = new LinkedList<>(availableSeats);
        return this;
    }

    public SeatInfo setSeats(Vector2... availableSeats) {
        this.availableSeats = new LinkedList<>(Arrays.asList(availableSeats));
        return this;
    }

    public String getName() {
        return name;
    }
}
