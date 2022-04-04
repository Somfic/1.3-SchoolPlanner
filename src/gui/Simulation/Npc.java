package gui.simulation;

import data.Person;
import data.Schedule;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

public abstract class Npc {
    private Vector2 position;
    private final Person person;

    public Npc(Person person) {
        this.person = person;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    abstract Vector2 getNextMove(Schedule schedule, int period, MapInfo mapInfo);
    abstract void giveUpSeat();
}
