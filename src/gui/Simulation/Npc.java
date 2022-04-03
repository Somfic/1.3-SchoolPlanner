package gui.simulation;

import data.Schedule;
import org.dyn4j.geometry.Vector2;

public abstract class Npc {
    private Vector2 position;

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    abstract Vector2 getNextMove(Schedule schedule, int period, MapInfo mapInfo);
}
